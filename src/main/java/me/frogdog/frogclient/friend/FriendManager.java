package me.frogdog.frogclient.friend;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.frogdog.frogclient.Frog;
import me.frogdog.frogclient.config.Config;
import me.frogdog.frogclient.util.registry.ListRegistry;

import java.io.*;
import java.util.ArrayList;

public final class FriendManager extends ListRegistry<Friend> {

    public FriendManager() {
        this.registry = new ArrayList();
        new Config("friends.json"){

            @Override
            public void load(Object... source) {
                JsonElement root;
                try {
                    if (!this.getFile().exists()) {
                        this.getFile().createNewFile();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                if (!this.getFile().exists()) {
                    return;
                }
                try (FileReader reader = new FileReader(this.getFile());){
                    root = new JsonParser().parse((Reader)reader);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                if (!(root instanceof JsonArray)) {
                    return;
                }
                JsonArray friends = (JsonArray)root;
                friends.forEach(node -> {
                    if (!(node instanceof JsonObject)) {
                        return;
                    }
                    try {
                        JsonObject friendNode = (JsonObject)node;
                        Frog.getInstance().getFriendManager().getRegistry().add(new Friend(friendNode.get("friend-label").getAsString(), friendNode.get("friend-alias").getAsString()));
                    }
                    catch (Throwable e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void save(Object... destination) {
                if (this.getFile().exists()) {
                    this.getFile().delete();
                }
                if (Frog.getInstance().getFriendManager().getRegistry().isEmpty()) {
                    return;
                }
                JsonArray friends = new JsonArray();
                Frog.getInstance().getFriendManager().getRegistry().forEach(friend -> {
                    try {
                        JsonObject friendObject;
                        JsonObject properties = friendObject = new JsonObject();
                        properties.addProperty("friend-label", friend.getLabel());
                        properties.addProperty("friend-alias", friend.getAlias());
                        friends.add((JsonElement)properties);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                try (FileWriter writer = new FileWriter(this.getFile());){
                    writer.write(new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)friends));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public Friend getFriendByAliasOrLabel(String aliasOrLabel) {
        for (Friend friend : registry) {
            if (!aliasOrLabel.equalsIgnoreCase(friend.getLabel()) && !aliasOrLabel.equalsIgnoreCase(friend.getAlias())) continue;
            return friend;
        }
        return null;
    }

    public boolean isFriend(String aliasOrLabel) {
        for (Friend friend : registry) {
            if (!aliasOrLabel.equalsIgnoreCase(friend.getLabel()) && !aliasOrLabel.equalsIgnoreCase(friend.getAlias())) continue;
            return true;
        }
        return false;
    }
}

