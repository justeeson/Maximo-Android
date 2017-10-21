package app.edutechnologic.projectmaximo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import static app.edutechnologic.projectmaximo.Maximo.workitem_list;

public class WorkItemFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_work_item, container, false);
        WorkItemAdapter work_item_adapter;
        //set the upper limit for displayed work item to be 10
        //if less the ten, display all
        if(workitem_list.size() >10){
            ArrayList<WorkItem> firstTen = new ArrayList<WorkItem>();
            for(int i = 0; i < 10; i++){
                firstTen.add(workitem_list.get(i));
            }
            work_item_adapter = new WorkItemAdapter(getContext(), firstTen);
        }else{
            work_item_adapter = new WorkItemAdapter(getContext(), workitem_list);
        }
        //set adapter for work item list
        ListView work_item_list = (ListView) view.findViewById(R.id.WorkItemList);
        work_item_list.setAdapter(work_item_adapter);

        return view;
    }

}
