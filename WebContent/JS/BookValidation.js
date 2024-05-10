/**
 * All Book Related Validation Methods. 
 */

// Methods For Validation Of Book BulkUpload.

function validateBookBulkUpload() {
	if (bookBulkUploadFileValidation()) {
		 document.getElementById("bookBulkUploadFileValidationCheck").style = 'visibility: visible; color: red; font-family: Cambria; font-size: 13px;';
		 event.preventDefault();
	 } else {
		 document.getElementById("bookBulkUploadFileValidationCheck").style = 'visibility: hidden;';
		 alert("Book Bulk Upload Started. This may take a few minutes depending on the file size.");
	 }
}

function bookBulkUploadFileValidation() {
	return (document.bookBulkUploadContainer1.bookBulkUploadFileInput.value == '');
}

//Methods For Validation Of An Book Addition. 
function validateAddition() {
	if (bookNameValidationForAddition()) {
		 document.getElementById("bookNameCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("bookNameCheck").style = 'visibility: hidden;';
	}
	
	if (authorNameValidationForAddition()) {
		 document.getElementById("authorNameCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("authorNameCheck").style = 'visibility: hidden;';
	}
	
	if (availabilityDateValidationForAddition()) {
		 document.getElementById("availabilityDateCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("availabilityDateCheck").style = 'visibility: hidden;';
	}
	
	if (genreValidationForAddition()) {
		 document.getElementById("genreCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("genreCheck").style = 'visibility: hidden;';
	}
}

function bookNameValidationForAddition() {
	return (!(isNaN(document.bookRegistrationContainer.bookName.value))
		|| document.bookRegistrationContainer.bookName.value == '');
}

function authorNameValidationForAddition() {
	return (!(isNaN(document.bookRegistrationContainer.authorName.value))
		|| document.bookRegistrationContainer.authorName.value == '');
}

function availabilityDateValidationForAddition() {
	return (!(isNaN(document.bookRegistrationContainer.availabilityDate.value))
		|| document.bookRegistrationContainer.availabilityDate.value == '');
}

function genreValidationForAddition() {
	return (document.bookRegistrationContainer.genreDropDownValues.value == '-1');
}