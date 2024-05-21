
document.addEventListener('DOMContentLoaded', function() {
    var openModalBtn = document.getElementById('openModalBtn');
    var modal = new bootstrap.Modal(document.getElementById('exampleModal'));
    var form = document.getElementById('categoryForm');
    var saveChangesBtn = document.getElementById('saveChanges');

    openModalBtn.addEventListener('click', function() {
        modal.show();
    });

    saveChangesBtn.addEventListener('click', function(event) {
        if (form.checkValidity()) {
            event.preventDefault(); // Prevent default form submission

            var actionUrl = form.getAttribute('action'); // Get the form's action attribute

            // Perform AJAX request using Fetch API
            fetch(actionUrl, {
                method: 'POST',
                body: new FormData(form)
            }).then(response => {
                if (response.ok) {
                    console.log('Form submitted successfully');
                    form.reset();
                    modal.hide();
                } else {
                    console.log('Form submission failed');
                }
            }).catch(error => {
                console.error('Error:', error);
            });
        } else {
            form.reportValidity();
        }
    });
});
