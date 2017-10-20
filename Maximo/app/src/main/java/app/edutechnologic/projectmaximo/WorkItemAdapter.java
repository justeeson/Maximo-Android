package app.edutechnologic.projectmaximo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by momoliu on 10/10/17.
 */

public class WorkItemAdapter extends ArrayAdapter<WorkItem> {
    public WorkItemAdapter(Context context, ArrayList<WorkItem> work_item) {
        super(context, 0, work_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        WorkItem work_item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.work_item_structure, parent, false);
        }
        // Lookup view for data population
        TextView work_item_view = (TextView) convertView.findViewById(R.id.work_item_textview);
        // Populate the data into the template view using the data object
        work_item_view.setText(work_item.work_item);
        // Return the completed view to render on screen
        return convertView;
    }
}
