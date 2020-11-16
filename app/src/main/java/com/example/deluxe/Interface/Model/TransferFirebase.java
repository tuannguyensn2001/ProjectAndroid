package com.example.deluxe.Interface.Model;

import com.example.deluxe.Enum.ErrorMessage;
import com.example.deluxe.Enum.SuccessMessage;

public interface TransferFirebase {

	void success(SuccessMessage successMessage);

	void failed(ErrorMessage errorMessage);
}
