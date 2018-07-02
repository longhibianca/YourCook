package br.edu.iff.pooa20181.yourcook.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20181.yourcook.ItemIngredienteViewHolder;
import br.edu.iff.pooa20181.yourcook.R;
import br.edu.iff.pooa20181.yourcook.model.ItemIngredienteDTO;

public class ListaIngredienteAdapter extends BaseAdapter {

    private List<ItemIngredienteDTO> itemIngredienteDTO = null;

    private Context ctx = null;

    public ListaIngredienteAdapter(Context ctx, List<ItemIngredienteDTO> itemIngredienteDTO) {
        this.ctx = ctx;
        this.itemIngredienteDTO = itemIngredienteDTO;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(itemIngredienteDTO!=null)
        {
            ret = itemIngredienteDTO.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int itemIndex) {
        Object ret = null;
        if(itemIngredienteDTO!=null) {
            ret = itemIngredienteDTO.get(itemIndex);
        }
        return ret;
    }

    @Override
    public long getItemId(int itemIndex) {
        return itemIndex;
    }

    @Override
    public View getView(int itemIndex, View convertView, ViewGroup viewGroup) {

        ItemIngredienteViewHolder viewHolder = null;

        if(convertView!=null)
        {
            viewHolder = (ItemIngredienteViewHolder) convertView.getTag();
        }else
        {
            convertView = View.inflate(ctx, R.layout.activity_item_ingrediente, null);

            CheckBox listItemCheckbox = (CheckBox) convertView.findViewById(R.id.item_ingrediente);

            TextView listItemText = (TextView) convertView.findViewById(R.id.item_ingred_texto);

            viewHolder = new ItemIngredienteViewHolder(convertView);

            viewHolder.setItemCheckbox(listItemCheckbox);

            viewHolder.setItemTextView(listItemText);

            convertView.setTag(viewHolder);
        }

        ItemIngredienteDTO ItemDto = itemIngredienteDTO.get(itemIndex);
        viewHolder.getItemCheckbox().setChecked(ItemDto.isChecked());
        viewHolder.getItemTextView().setText(ItemDto.getItemText());

        return convertView;
    }
}
