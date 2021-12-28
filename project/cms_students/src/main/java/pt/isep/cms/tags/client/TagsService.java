package pt.isep.cms.tags.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import pt.isep.cms.tags.shared.Tag;
import pt.isep.cms.tags.shared.TagDetails;

import java.util.ArrayList;

@RemoteServiceRelativePath("tagsService")
public interface TagsService extends RemoteService {

    Tag addTag(Tag tag);

    Boolean deleteTag(String id);

    Boolean validDescription(String description);

    ArrayList<TagDetails> deleteTags(ArrayList<String> ids);

    ArrayList<TagDetails> getTagsDetails();

    Tag getTag(String id);

    Tag updateTag(Tag tag);
}
