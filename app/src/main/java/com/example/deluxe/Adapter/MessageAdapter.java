package com.example.deluxe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Entity.Message;
import com.example.deluxe.Helper.ConvertData;
import com.example.deluxe.Helper.Rules;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.R;
import com.example.deluxe.View.Components.ReplyTransactionDialog;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
	ArrayList<Message> listMessage;
	Context context;

	public MessageAdapter(ArrayList<Message> list, Context context) {
		this.listMessage = list;
		this.context = context;
	}

	public void setList(ArrayList<Message> list) {
		this.listMessage = list;
	}


	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
		View itemView;
		switch (viewType) {
			case 0:
				itemView = layoutInflater.inflate(R.layout.component_chat_message_left, parent, false);
				break;
			case 1:
				itemView = layoutInflater.inflate(R.layout.component_chat_message_right, parent, false);
				break;
			case 2:
				itemView = layoutInflater.inflate(R.layout.layout_transfer_message_left, parent, false);
				break;
			default:
				itemView = layoutInflater.inflate(R.layout.layout_transfer_message_right, parent, false);
				break;
		}

		return new ViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
		final Message message = listMessage.get(position);
		View transactionPart, statusPart, actionPart;
		TextView transactionTitle, transactionStatus, transactionMoneyFirst, transactionMoneySecond;
		String title, status, moneyFirst, moneySecond;

		String content = listMessage.get(position).getContent();
		content = Rules.isSpace(listMessage.get(position).getContent()) ? context.getString(R.string.message_content_null) : content;
		holder.message.setText(content);

		if (message.getType() != 0) {
			String tao = Auth.getInstance().user().getEmail();


			transactionPart = holder.itemView.findViewById(R.id.transaction_part);
			statusPart = holder.itemView.findViewById(R.id.status_part);
			actionPart = holder.itemView.findViewById(R.id.action_part);
			transactionTitle = holder.itemView.findViewById(R.id.transaction_title);
			transactionStatus = holder.itemView.findViewById(R.id.transaction_status);
			transactionMoneyFirst = holder.itemView.findViewById(R.id.transaction_money_first);
			transactionMoneySecond = holder.itemView.findViewById(R.id.transaction_money_second);

			moneySecond = ConvertData.moneyToString(message.getSecondMoney());

			if (message.getType() == -1) {
				transactionPart.setVisibility(View.VISIBLE);
				statusPart.setVisibility(View.GONE);
				actionPart.setVisibility(View.GONE);
			} else if (message.getType() == 1) {
				transactionPart.setVisibility(View.VISIBLE);
				statusPart.setVisibility(View.VISIBLE);
				if (message.getStatus() == 1) {
					actionPart.setVisibility(View.GONE);
					transactionMoneySecond.setVisibility(View.VISIBLE);

					transactionStatus.setText(context.getString(R.string.message_transaction_status_done));
					transactionStatus.setTextColor(context.getColor(R.color.light_mainColor));
				} else if (message.getStatus() == 0) {
					actionPart.setVisibility(View.VISIBLE);
					transactionMoneySecond.setVisibility(View.GONE);

					transactionStatus.setText(context.getString(R.string.message_transaction_status_waiting));
					transactionStatus.setTextColor(context.getColor(R.color.yellow));
				} else {
					actionPart.setVisibility(View.GONE);
					transactionMoneySecond.setVisibility(View.GONE);

					transactionStatus.setText(context.getString(R.string.message_transaction_status_cancel));
					transactionStatus.setTextColor(context.getColor(R.color.red));
				}
			}

			if (message.getEmailSender().equals(tao)) {
				actionPart.setVisibility(View.GONE);

				if (message.getType() == 1) {
					title = context.getString(R.string.message_transaction_title, tao, "đòi", "nó");
					moneyFirst = "+ " + ConvertData.moneyToString(message.getFirstMoney());
					transactionMoneyFirst.setTextColor(context.getColor(R.color.light_hint));

					if (message.getStatus() == 1) {
						moneySecond = "+ " + moneySecond;
						transactionMoneySecond.setTextColor(context.getColor(R.color.green));
						transactionMoneySecond.setText(moneySecond);
					}
				} else {
					title = context.getString(R.string.message_transaction_title, tao, "gửi", "nó");
					moneyFirst = "- " + ConvertData.moneyToString(message.getFirstMoney());
					transactionMoneyFirst.setTextColor(context.getColor(R.color.red));
				}
			} else {
				if (message.getType() == 1) {
					title = context.getString(R.string.message_transaction_title, "Nó", "đòi", tao);
					moneyFirst = "- " + ConvertData.moneyToString(message.getFirstMoney());
					transactionMoneyFirst.setTextColor(context.getColor(R.color.light_hint));

					if (message.getStatus() == 1) {
						moneySecond = "- " + moneySecond;
						transactionMoneySecond.setTextColor(context.getColor(R.color.red));
						transactionMoneySecond.setText(moneySecond);
					}
				} else {
					title = context.getString(R.string.message_transaction_title, "Nó", "gửi", tao);
					moneyFirst = "+ " + ConvertData.moneyToString(message.getFirstMoney());
					transactionMoneyFirst.setTextColor(context.getColor(R.color.green));
				}
			}
			transactionTitle.setText(title);
			transactionMoneyFirst.setText(moneyFirst);
		}


//		Cac loai nut bam
		if (message.getType() == 1 && message.getStatus() == 0
				&& !message.getEmailSender().equals(Auth.getInstance().user().getEmail())) {
			holder.itemView.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					new ReplyTransactionDialog(context, message.getFirstMoney(), message.getId());
//					String messageId = message.getId();
//					message.setStatus(1);
//					message.setSecondMoney(message.getFirstMoney());
//					listMessage.set(position, message);
//					((com.example.deluxe.Core.View.ViewUseSendTransaction) context).updateTransaction(message);
				}
			});

			holder.itemView.findViewById(R.id.negative_button).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String messageId = message.getId();
					message.setStatus(-1);
					message.setSecondMoney(0);
					listMessage.set(position, message);
					((com.example.deluxe.Core.View.ViewUseSendTransaction) context).updateTransaction(message);
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return this.listMessage.size();
	}

	@Override
	public int getItemViewType(int position) {
		Message message = listMessage.get(position);

		if (message.getType() == 0) {
			if (message.getEmailSender().equals(Auth.getInstance().user().getEmail())) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (message.getEmailSender().equals(Auth.getInstance().user().getEmail())) {
				return 3;
			} else {
				return 2;
			}
		}
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		TextView message;

		View itemView;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			this.itemView = itemView;

			message = itemView.findViewById(R.id.message);
		}
	}
}