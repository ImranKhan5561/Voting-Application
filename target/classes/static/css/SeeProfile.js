 function editField(field) {
        document.getElementById(field + '-display').style.display = 'none';
        document.getElementById(field + '-input').style.display = 'inline';
        document.querySelector(`#${field}-field .save-btn`).style.display = 'inline';
        document.querySelector(`#${field}-field .cancel-btn`).style.display = 'inline';
        document.querySelector(`#${field}-field .edit-btn`).style.display = 'none';
    }

    function saveField(field) {
        const inputValue = document.getElementById(field + '-input').value;
        document.getElementById(field + '-display').textContent = inputValue;

        // Reset to display mode
        cancelEdit(field);

        console.log(`Updated ${field} to: ${inputValue}`);
    }

    function cancelEdit(field) {
        document.getElementById(field + '-display').style.display = 'inline';
        document.getElementById(field + '-input').style.display = 'none';
        document.querySelector(`#${field}-field .edit-btn`).style.display = 'inline';
        document.querySelector(`#${field}-field .save-btn`).style.display = 'none';
        document.querySelector(`#${field}-field .cancel-btn`).style.display = 'none';
    }
