package pt.isep.cms.tags.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import pt.isep.cms.tags.shared.Tag;
import pt.isep.cms.tags.shared.TagDetails;

import java.util.ArrayList;

public interface TagsServiceAsync {

    public void addTag(Tag tag, AsyncCallback<Tag> callback);

    public void deleteTag(String id, AsyncCallback<Boolean> callback);

    public void validDescription(String description, AsyncCallback<Boolean> callback);

    public void deleteTags(ArrayList<String> ids, AsyncCallback<ArrayList<TagDetails>> callback);

    public void getTagsDetails(AsyncCallback<ArrayList<TagDetails>> callback);

    public void getTag(String id, AsyncCallback<Tag> callback);

    public void updateTag(Tag tag, AsyncCallback<Tag> callback);
}
