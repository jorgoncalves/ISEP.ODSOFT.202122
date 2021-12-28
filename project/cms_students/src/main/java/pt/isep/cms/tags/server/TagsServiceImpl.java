package pt.isep.cms.tags.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pt.isep.cms.tags.client.TagsService;
import pt.isep.cms.tags.shared.Tag;
import pt.isep.cms.tags.shared.TagDetails;

@SuppressWarnings("serial")
public class TagsServiceImpl extends RemoteServiceServlet implements
        TagsService {

    private static final String[] tagsData = new String[]{
            "Action and Adventure", "Classics", "Comic Book or Graphic Novel",
            "Detective and Mystery", "Fantasy", "Historical Fiction", "Horror",
            "Literary Fiction"};

    private final HashMap<String, Tag> tags = new HashMap<String, Tag>();

    public TagsServiceImpl() {
        initTags();
    }

    private void initTags() {
        // TODO: Create a real UID for each tag
        //
        for (int i = 0; i < tagsData.length; ++i) {
            Tag tag = new Tag(String.valueOf(i), tagsData[i]);
            tags.put(tag.getId(), tag);
        }
    }

    public Tag addTag(Tag tag) {
        tag.setId(String.valueOf(tags.size()));
        tags.put(tag.getId(), tag);
        return tag;
    }

    public Tag updateTag(Tag tag) {
        String lid = tag.getId();
        tags.remove(tag.getId());
        tags.put(tag.getId(), tag);
        return tag;
    }

    public Boolean deleteTag(String id) {
        tags.remove(id);
        return true;
    }

    @Override
    public Boolean validDescription(String description) {
        Boolean isValid = true;

        Iterator<String> it = tags.keySet().iterator();

//        for (int i = 0; i < tags.size(); i++) {
//
//        }

        while (it.hasNext()) {
            Tag tag = tags.get(it.next());
            System.out.println(tag.getDescription() + " - "+ description);
            if (tag.getDescription().equals(description)) {
                isValid = false;
                System.out.println("I'M HERE MDF");
                break;
            }
        }
        System.out.println("isValid - " + isValid.toString());
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

        Iterator<String> it = tags.keySet().iterator();
        while (it.hasNext()) {
            Tag tag = tags.get(it.next());
            tagDetails.add(tag.getLightWeightTag());
        }

        return tagDetails;
    }

    public Tag getTag(String id) {
        return tags.get(id);
    }
}
