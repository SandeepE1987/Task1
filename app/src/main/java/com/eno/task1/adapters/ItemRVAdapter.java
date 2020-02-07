package com.eno.task1.adapters;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eno.task1.DialogManager;
import com.eno.task1.R;
import com.eno.task1.Utils;
import com.eno.task1.models.ItemModel;
import com.eno.task1.widgets.CustomEditText;
import com.eno.task1.widgets.NumberTextWatcher;

import java.util.List;

/*
 *
 * Inflate dynamic list of items using RecyclerView.Adapter
 * @author Sandeep Emekar
 * @since 07/02/2020
 *
 * */

public class ItemRVAdapter extends RecyclerView.Adapter<ItemRVAdapter.ItemRVViewHolder> {

    private static final double MIN_VALUE_REQUIRED = 1.99;
    private static final double MAX_VALUE_REQUIRED = 5.99;
    Activity activity;
    List<ItemModel> itemModelList;
    LayoutInflater layoutInflater;
    private int POSITION_FOR_SOFT_FOCUS = 0;


    public ItemRVAdapter(Activity activity, List<ItemModel> itemModelList) {
        this.activity = activity;
        this.itemModelList = itemModelList;
        this.layoutInflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ItemRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_item_view, parent, false);
        return new ItemRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemRVViewHolder itemViewHolder, final int position) {
        final ItemModel itemModel = itemModelList.get(position);

        if (itemModel.getValue() != null)
            itemViewHolder.edtValue.setText(itemModel.getValue());

        setEditTextVisible(itemViewHolder, itemModel.isVisible());

        /*Put soft input focus on edittext*/
        if (POSITION_FOR_SOFT_FOCUS == position && itemModel.isVisible()) {
            Utils.showSoftInput(activity, itemViewHolder.edtValue);
            itemViewHolder.edtValue.setSelection(itemViewHolder.edtValue.getText().toString().length());
        }

        itemViewHolder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((position - 1) >= 0) {
                    POSITION_FOR_SOFT_FOCUS = position - 1;
                    ItemModel itemModelToSetVisible = itemModelList.get(POSITION_FOR_SOFT_FOCUS);
                    itemModelToSetVisible.setVisible(true);
                    notifyItemChanged(POSITION_FOR_SOFT_FOCUS);
                }
            }
        });

        /*TextWatcher to validate X.XX number format*/
        TextWatcher tw = new NumberTextWatcher(itemViewHolder.edtValue);
        itemViewHolder.edtValue.addTextChangedListener(tw);
        itemViewHolder.edtValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    checkNumberRange(itemModel, itemViewHolder, position);
                }
                return false;
            }
        });

        itemViewHolder.edtValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (itemViewHolder.edtValue.isFocused()) {
                    itemModel.setValue(itemViewHolder.edtValue.getText().toString());
                    itemModelList.set(position, itemModel);
                }
            }
        });


        /*OnBackPressed Listener to check entered value*/
        itemViewHolder.edtValue.setOnBackPressedListener(new CustomEditText.OnBackPressedListener() {
            @Override
            public void onBackPressed() {
                if (!itemViewHolder.edtValue.getText().toString().isEmpty() && itemModel.isVisible() && itemViewHolder.edtValue.isFocused()) {
                    checkNumberRange(itemModel, itemViewHolder, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    private void checkNumberRange(final ItemModel itemModel, final ItemRVViewHolder itemRVViewHolder, final int position) {
        if (Double.parseDouble(itemModel.getValue()) < MIN_VALUE_REQUIRED || Double.parseDouble(itemModel.getValue()) > MAX_VALUE_REQUIRED) {
            DialogManager.showDiscardUpdateDialog(activity, activity.getString(R.string.confirmation), activity.getString(R.string.value_is_not_within_range), new DialogManager.ConfirmationListener() {
                @Override
                public void onClickYes() {
                    itemRVViewHolder.edtValue.clearFocus();
                }

                @Override
                public void onClickNo() {
                    itemModel.setValue("0.00");
                    itemModel.setVisible(false);
                    itemModelList.set(position, itemModel);
                    notifyItemChanged(position);

                    if ((position + 1) < itemModelList.size()) {
                        POSITION_FOR_SOFT_FOCUS = position + 1;
                        ItemModel itemModelToEdit = itemModelList.get(POSITION_FOR_SOFT_FOCUS);
                        itemModelToEdit.setVisible(true);
                        notifyItemChanged(POSITION_FOR_SOFT_FOCUS);
                    }
                }
            });
        }
    }

    /*Set visibility of edtValue and tvText*/
    private void setEditTextVisible(ItemRVViewHolder itemViewHolder, boolean visible) {
        itemViewHolder.tvText.setVisibility(visible ? View.GONE : View.VISIBLE);
        itemViewHolder.edtValue.setVisibility(visible ? View.VISIBLE : View.GONE);
        /*Commented code to avoid btnStart getting disabled for another unopened view*/
        // itemViewHolder.btnStart.setVisibility(visible ? View.GONE : View.VISIBLE);
    }

    class ItemRVViewHolder extends RecyclerView.ViewHolder {
        TextView tvText;
        CustomEditText edtValue;
        Button btnStart;

        public ItemRVViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
            edtValue = itemView.findViewById(R.id.edtValue);
            btnStart = itemView.findViewById(R.id.btnStart);
        }
    }
}
