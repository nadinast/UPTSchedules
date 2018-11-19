package com.apps.uptschedules.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<SubjectItem> ITEMS = new ArrayList<SubjectItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, SubjectItem> ITEM_MAP = new HashMap<String, SubjectItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(String.valueOf(i)));
        }
    }

    private static void addItem(SubjectItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static SubjectItem createDummyItem(String id, String time_interval, String subject_name, String subject_session, String session_room, String session_prof) {
        return new SubjectItem(id, time_interval,  subject_name,  subject_session,  session_room,  session_prof);
    }
    private static SubjectItem createDummyItem(String id) {
        return new SubjectItem(id);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class SubjectItem {
        public String id;
        public String time_interval = null;
        public String subject_name = null;
        public String subject_session = null;
        public String session_room = null;
        public String session_prof = null;

        public SubjectItem(String id, String time_interval, String subject_name, String subject_session, String session_room, String session_prof) {
            this.time_interval = time_interval;
            this.subject_name = subject_name;
            this.subject_session = subject_session;
            this.session_room = session_room;
            this.session_prof = session_prof;
        }

        public SubjectItem(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return this.subject_name + "(" + subject_session + ")";
        }
    }
}
