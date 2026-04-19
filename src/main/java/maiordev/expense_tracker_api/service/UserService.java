package maiordev.expense_tracker_api.service;

import maiordev.expense_tracker_api.util.GenericResponse;

public interface UserService {
    GenericResponse registerUser();
    GenericResponse loginUser();
}
