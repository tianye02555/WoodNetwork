package woodnetwork.hebg3.com.phone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;

import woodnetwork.hebg3.com.phone.R;

/**
 * Created by ty on 2016/11/24 0024.
 */

public class FileChooseAdapter extends RecyclerView.Adapter<FileChooseAdapter.ViewHolder> {
    private Context context;

    public File[] getFileName() {
        return fileName;
    }

    public void setFileName(File[] fileName) {
        this.fileName = fileName;
    }

    private File[] fileName;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ItemClickListener itemClickListener;

    public FileChooseAdapter(File[] fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
    }

    @Override
    public FileChooseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FileChooseAdapter.ViewHolder viewHolder=null;
        switch (viewType){
            case 0:
                viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.filechoose_back, parent, false));
                break;
            case 1:
                viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.filechoose, parent, false));
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FileChooseAdapter.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 1:
                String fileNam = fileName[position-1].getAbsolutePath().substring(fileName[position-1].getAbsolutePath().lastIndexOf("/") + 1);
                holder.fileName.setText(fileNam);
                break;
        }


    }

    @Override
    public int getItemCount() {
        if(null==fileName){
            return 1;
        }
        return fileName.length+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(0==position){
            return 0;
        }
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fileName;

        public ViewHolder(View itemView) {
            super(itemView);
            fileName = (TextView) itemView.findViewById(R.id.filename);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (getItemViewType()){
                        case 0:
                            if (null != itemClickListener) {
                                itemClickListener.setOnBack(view, getAdapterPosition()-1);
                            }
                            break;
                        case 1:
                            if (null != itemClickListener) {
                                itemClickListener.onItemClick(view, getAdapterPosition()-1);
                            }
                            break;
                    }
                }
            });
        }
    }

   public interface ItemClickListener {
        public void onItemClick(View view, int position);
        public void setOnBack(View view, int position);
    }
}
