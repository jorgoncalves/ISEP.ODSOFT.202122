package pt.isep.cms.tags.server;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import pt.isep.cms.contacts.shared.Contact;
import pt.isep.cms.tags.client.TagsService;
import pt.isep.cms.tags.shared.Tag;
import pt.isep.cms.tags.shared.TagDetails;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@SuppressWarnings("serial")
public class TagsServiceImpl extends RemoteServiceServlet implements
        TagsService {

    private static final String[] tagsData = new String[]{
            "Action and Adventure", "Classics", "Comic Book or Graphic Novel",
            "Detective and Mystery", "Fantasy", "Historical Fiction", "Horror",
            "Literary Fiction"};

    private EntityManagerFactory emfactory = null;
    private EntityManager entitymanager = null;

    public TagsServiceImpl() {
        this.emfactory = Persistence.createEntityManagerFactory("CMS");

        this.entitymanager = emfactory.createEntityManager();

        initPersistentTags();
    }

    private void initPersistentTags() {
        Query query = entitymanager.createQuery("Select COUNT(t) from Tag t");
        Long result = (Long) query.getSingleResult();

        if (result == 0) {
            System.out.println("No tags found. Populating db.");
            this.entitymanager.getTransaction().begin();

            for (int i = 0; i < tagsData.length; ++i) {
                Tag tag = new Tag(tagsData[i]);
                this.entitymanager.persist(tag);
            }

            this.entitymanager.getTransaction().commit();

        }
    }

    public Tag addTag(Tag tag) {
        this.entitymanager.getTransaction().begin();
        this.entitymanager.persist(tag);
        this.entitymanager.getTransaction().commit();

        return tag;
    }

    public Tag updateTag(Tag tag) {
        this.entitymanager.getTransaction().begin();
        this.entitymanager.merge(tag);
        this.entitymanager.getTransaction().commit();

        return tag;
    }

    public Boolean deleteTag(String id) {
        this.entitymanager.getTransaction().begin();
        Tag tag = entitymanager.find(Tag.class, id);
        entitymanager.remove(tag);
        this.entitymanager.getTransaction().commit();

        return true;
    }

    @Override
    public Boolean validDescription(String description) {
        Boolean isValid = true;

        Query query = entitymanager.createQuery("Select t from Tag t");

        @SuppressWarnings("unchecked")
        List<Tag> list = query.getResultList();

        for (Tag tag : list) {
            if (tag.getDescription().equals(description)) {
                isValid = false;

                break;
            }
        }

        return isValid;
    }

    public ArrayList<TagDetails> deleteTags(ArrayList<String> ids) {

        for (int i = 0; i < ids.size(); ++i) {
            deleteTag(ids.get(i));
        }

        return getTagsDetails();
    }

    public ArrayList<TagDetails> getTagsDetails() {
        ArrayList<TagDetails> tagDetails = new ArrayList<TagDetails>();

        Query query = entitymanager.createQuery("Select t from Tag t");

        @SuppressWarnings("unchecked")
        List<Tag> list = query.getResultList();

        for (Tag tag : list) {
            tagDetails.add(tag.getLightWeightTag());
        }

        return tagDetails;
    }

    public Tag getTag(String id) {
        return entitymanager.find(Tag.class, id);
    }
}
