package com.tianque.service;

import java.util.List;

import com.tianque.domain.User;

public interface AutocompleteService {
	
	List<User> findUserForAutocomplete(String name,User user) ;
}
