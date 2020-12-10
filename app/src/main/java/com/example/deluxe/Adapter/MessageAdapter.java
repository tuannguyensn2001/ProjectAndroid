package com.example.deluxe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deluxe.Entity.Message;
import com.example.deluxe.Model.Auth;
import com.example.deluxe.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

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
		holder.message.setText(listMessage.get(position).getContent());

		if (message.getType() != 0) {
			String tao = Auth.getInstance().user().getEmail();

			View transactionPart, statusPart, actionPart;
			TextView transactionTitle, transactionStatus, transactionMoneyFirst, transactionMoneySecond;
			String title, status, moneyFirst, moneySecond;

			transactionPart = holder.itemView.findViewById(R.id.transaction_part);
			statusPart = holder.itemView.findViewById(R.id.status_part);
			actionPart = holder.itemView.findViewById(R.id.action_part);
			transactionTitle = holder.itemView.findViewById(R.id.transaction_title);
			transactionStatus = holder.itemView.findViewById(R.id.transaction_status);
			transactionMoneyFirst = holder.itemView.findViewById(R.id.transaction_money_first);
			transactionMoneySecond = holder.itemView.findViewById(R.id.transaction_money_second);

//			transactionTitle.setText("Da co mot giao dich gui tien goi la");
//			transactionMoneyFirst.setText(new DecimalFormat("#,###,###").format(message.getFirstMoney()));
			moneySecond = new DecimalFormat("#,###,###").format(message.getSecondMoney());

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
					moneyFirst = "+ " + new DecimalFormat("#,###,###").format(message.getFirstMoney());
					transactionMoneyFirst.setTextColor(context.getColor(R.color.light_hint));

					if (message.getStatus() == 1) {
						moneySecond = "+ " + moneySecond;
						transactionMoneySecond.setTextColor(context.getColor(R.color.green));
						transactionMoneySecond.setText(moneySecond);
					}
				} else {
					title = context.getString(R.string.message_transaction_title, tao, "gửi", "nó");
					moneyFirst = "- " + new DecimalFormat("#,###,###").format(message.getFirstMoney());
					transactionMoneyFirst.setTextColor(context.getColor(R.color.red));
				}
			} else {
				if (message.getType() == 1) {
					title = context.getString(R.string.message_transaction_title, "Nó", "đòi", tao);
					moneyFirst = "- " + new DecimalFormat("#,###,###").format(message.getFirstMoney());
					transactionMoneyFirst.setTextColor(context.getColor(R.color.light_hint));

					if (message.getStatus() == 1) {
						moneySecond = "- " + moneySecond;
						transactionMoneySecond.setTextColor(context.getColor(R.color.red));
						transactionMoneySecond.setText(moneySecond);
					}
				} else {
					title = context.getString(R.string.message_transaction_title, "Nó", "gửi", tao);
					moneyFirst = "+ " + new DecimalFormat("#,###,###").format(message.getFirstMoney());
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
				}
			});

			holder.itemView.findViewById(R.id.negative_button).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					message.setStatus(-1);
					listMessage.set(position, message);
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

		if (message.getFirstMoney() == 0) {
			Random vocungngaunhien = new Random();
			message.setType(vocungngaunhien.nextInt(3) - 1);
			message.setStatus(vocungngaunhien.nextInt(3) - 1);
			message.setFirstMoney(vocungngaunhien.nextInt(100000));
			message.setSecondMoney(vocungngaunhien.nextInt(100000));
			listMessage.set(position, message);
		}

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

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView message;

		View itemView;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			this.itemView = itemView;

			message = (TextView) itemView.findViewById(R.id.message);
		}
	}
}