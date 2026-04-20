package maiordev.expense_tracker_api.service;

import maiordev.expense_tracker_api.model.AuthRequest;
import maiordev.expense_tracker_api.util.GenericResponse;

public interface UserService {
    GenericResponse loginUser(AuthRequest authRequest);
    GenericResponse registerUser(AuthRequest authRequest);
}
