/**
 * All User Related Validation Methods. 
 */

// Methods For Validation Of An User Update.
function validateGetUserDetailsBeforeUpdate() {
	 if (userDropDownValidationForUpdation()) {
		 document.getElementById("userDropDownValidationCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userDropDownValidationCheck").style = 'visibility: hidden;'; 
	 }
}

function userDropDownValidationForUpdation() {
	return (document.userUpdationContainer1.userDropDownValuesForUpdation.value == -1);
}

function validateUpdate() {
	if (userFirstNameValidationForUpdate()) {
		 document.getElementById("userFirstNameCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userFirstNameCheck").style = 'visibility: hidden;';
	}

	if (userLastNameValidationForUpdate()) {
		 document.getElementById("userLastNameCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userLastNameCheck").style = 'visibility: hidden;';
	}
	
	if (userAddressValidationForUpdate()) {
		 document.getElementById("userAddressCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userAddressCheck").style = 'visibility: hidden;';
	}
	
	if (userMobileValidationForUpdate()) {
		 document.getElementById("userMobileCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userMobileCheck").style = 'visibility: hidden;';
	}
	
	if (userEmailValidationForUpdate()) {
		 document.getElementById("userEmailCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userEmailCheck").style = 'visibility: hidden;';
	}
	
	if (userAgeValidationForUpdate()) {
		 document.getElementById("userAgeCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userAgeCheck").style = 'visibility: hidden;';
	}
}

function userFirstNameValidationForUpdate() {
	return (!(isNaN(document.userUpdationContainer1.userFirstName.value))
		|| document.userUpdationContainer1.userFirstName.value == '');
}

function userLastNameValidationForUpdate() {
	return (!(isNaN(document.userUpdationContainer1.userLastName.value))
		|| document.userUpdationContainer1.userLastName.value == '');
}

function userAddressValidationForUpdate() {
	return (!(isNaN(document.userUpdationContainer1.userAddress.value))
		|| document.userUpdationContainer1.userAddress.value == '');
}

function userMobileValidationForUpdate() {
	return (isNaN(document.userUpdationContainer1.userMobile.value)
		|| document.userUpdationContainer1.userMobile.value == ''
		|| document.userUpdationContainer1.userMobile.value.length != 10);
}

function userEmailValidationForUpdate() {
	return (!(isNaN(document.userUpdationContainer1.userEmail.value))
		|| document.userUpdationContainer1.userEmail.value == ''
		|| !(document.userUpdationContainer1.userEmail.value.includes('@'))
		|| !(document.userUpdationContainer1.userEmail.value.includes('.com')));
}

function userAgeValidationForUpdate() {
	return (isNaN(document.userUpdationContainer1.userAge.value)
		|| document.userUpdationContainer1.userAge.value == ''
		|| document.userUpdationContainer1.userAge.value < 10);
}

// Methods For Validation Of An User Deletion. 
function validateDeletion() {
	 if (!userDropDownValidationForDeletion()) {
		 document.getElementById("userDropDownValidationCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userDropDownValidationCheck").style = 'visibility: hidden;'; 
	 }
}

function userDropDownValidationForDeletion() {
	return !(document.userDeletionContainer.userDropDownValuesForDeletion.value == -1);
}

//Methods For Validation Of An User Addition. 
function validateAddition() {
	if (userFirstNameValidationForAddition()) {
		 document.getElementById("userFirstNameCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userFirstNameCheck").style = 'visibility: hidden;';
	}
	
	if (userLastNameValidationForAddition()) {
		 document.getElementById("userLastNameCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userLastNameCheck").style = 'visibility: hidden;';
	}
	
	if (userAddressValidationForAddition()) {
		 document.getElementById("userAddressCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userAddressCheck").style = 'visibility: hidden;';
	}
	
	if (userMobileValidationForAddition()) {
		 document.getElementById("userMobileCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userMobileCheck").style = 'visibility: hidden;';
	}
	
	if (userEmailValidationForAddition()) {
		 document.getElementById("userEmailCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userEmailCheck").style = 'visibility: hidden;';
	}
	
	if (userAgeValidationForAddition()) {
		 document.getElementById("userAgeCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userAgeCheck").style = 'visibility: hidden;';
	}
	
	if (userGenderValidationForAddition()) {
		 document.getElementById("userGenderCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userGenderCheck").style = 'visibility: hidden;';
	}
}

function userFirstNameValidationForAddition() {
	return (!(isNaN(document.userRegistrationContainer.userFirstName.value))
		|| document.userRegistrationContainer.userFirstName.value == '');
}

function userLastNameValidationForAddition() {
	return (!(isNaN(document.userRegistrationContainer.userLastName.value))
		|| document.userRegistrationContainer.userLastName.value == '');
}

function userAddressValidationForAddition() {
	return (!(isNaN(document.userRegistrationContainer.userAddress.value))
		|| document.userRegistrationContainer.userAddress.value == '');
}

function userMobileValidationForAddition() {
	return (isNaN(document.userRegistrationContainer.userMobile.value)
		|| document.userRegistrationContainer.userMobile.value == ''
		|| document.userRegistrationContainer.userMobile.value.length != 10);
}

function userEmailValidationForAddition() {
	return (!(isNaN(document.userRegistrationContainer.userEmail.value))
		|| document.userRegistrationContainer.userEmail.value == ''
		|| !(document.userRegistrationContainer.userEmail.value.includes('@'))
		|| !(document.userRegistrationContainer.userEmail.value.includes('.com')));
}

function userAgeValidationForAddition() {
	return (isNaN(document.userRegistrationContainer.userAge.value)
		|| document.userRegistrationContainer.userAge.value == ''
		|| document.userRegistrationContainer.userAge.value < 10);
}

function userGenderValidationForAddition() {
	return (document.userRegistrationContainer.userGender.value == '-1');
}