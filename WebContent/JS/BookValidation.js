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