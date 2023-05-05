const URL_CRUD = "/api/admin/users/";
const URL_ROLES = "/api/admin/users/roles";

const ROLES = getRoles();

const modalEdit = new bootstrap.Modal(document.querySelector("#modalEdit"));
const modalDelete = new bootstrap.Modal(document.querySelector("#modalDelete"));

let formEdit = document.forms.editForm;
let formDelete = document.forms.deleteForm;
let formSave = document.forms.saveForm;

const DELETE = "delete";
getAddForm();
getTablePerson();
async function getTablePerson() {
    await fetch(URL_CRUD)
        .then(resp => resp.json())
        .then(persons => {
            let table = document.getElementById('tablePersons');
            for (let person of persons) {


                let tr = document.createElement('tr');

                let td1 = document.createElement('td');
                td1.textContent = person.id;
                tr.appendChild(td1);

                let td2 = document.createElement('td');
                td2.textContent = person.name;
                tr.appendChild(td2);

                let td3 = document.createElement('td');
                td3.textContent = person.email;
                tr.appendChild(td3);

                let td4 = document.createElement('td');
                td4.textContent = person.address;
                tr.appendChild(td4);

                let td5 = document.createElement('td');
                td5.textContent = person.age;
                tr.appendChild(td5);

                let td6 = document.createElement('td');
                let roles = "";
                for (let rol of person.roles) {
                    roles += rol.role.substring(5) + " ";
                }
                td6.textContent = roles;
                tr.appendChild(td6);

                let td7 = document.createElement('td');
                let buttonEdit = document.createElement('button')
                buttonEdit.className = "btn btn-info text-white";
                buttonEdit.type = "button";
                buttonEdit.textContent = "Edit";
                buttonEdit.id = "buttonEdit";
                buttonEdit.setAttribute("data-toggle", "modal");
                buttonEdit.setAttribute("onclick", "getEditModalWindow(" + person.id + ")");
                td7.appendChild(buttonEdit);
                tr.appendChild(td7);

                let td8 = document.createElement('td');
                let buttonDelete = document.createElement('button')
                buttonDelete.className = "btn btn-danger";
                buttonDelete.type = "button";
                buttonDelete.textContent = "Delete";
                buttonDelete.id = "buttonDelete";
                buttonDelete.setAttribute("data-toggle", "modal");
                buttonDelete.setAttribute("onclick", "getDeleteModalWindow(" + person.id + ")");
                td8.appendChild(buttonDelete);
                tr.appendChild(td8);

                table.appendChild(tr);
            }
        })
}

async function getAddForm() {
    let fSave = formSave;
    ROLES.then(roles => {
        for (let rol of roles) {
            let role = document.createElement('option');
            role.setAttribute("value", rol.role)
            role.text = rol.role.substring(5);
            fSave.rolesSav.appendChild(role);
        }
        let saveButton = document.getElementById("saveButton")
        saveButton.setAttribute("onclick", "save()");
    })
}

async function save() {
    const formData = new FormData(formSave);
    let personRoles = [];
    ROLES
        .then(roles => {
            let option = formSave.rolesSav.options;
            for(let i = 0; i < roles.length; i++) {
                if (option[i].selected && option[i].value == roles[i].role) {
                    personRoles.push(roles[i]);
                }
            }
            return personRoles;
        })
        .then(personRoles => {
            fetch(URL_CRUD, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    "name": formData.get("nameSav"),
                    "email": formData.get("emailSav"),
                    "address": formData.get("addressSav"),
                    "age": formData.get("ageSav"),
                    "password": formData.get("passwordSav"),
                    "roles": personRoles
                })
            }).then(res => res.json());
        });
    await getTablePerson();
}

async function getEditModalWindow(id) {
let form = formEdit;
    ROLES.then(rols => {
        form.id.value = id;
        getPersonByID(id).then(person => {
            for (let rol of rols) {
                let role = document.createElement('option');
                role.setAttribute("value", rol.role)
                role.text = rol.role.substring(5);
                form.roles.appendChild(role);
            }
            form.name.value = person.name;
            form.email.value = person.email;
            form.address.value = person.address;
            form.age.value = person.age;
        })

        let buttonEdit = document.getElementById("editButton");
        buttonEdit.setAttribute("onclick", "editPerson(" + id + ")");
        modalEdit.show();

    })
}

async function getDeleteModalWindow(id){
    let fDelete = formDelete;
    ROLES.then(rols => {
        fDelete.idDel.value = id;
        getPersonByID(id).then(person => {
            for (let rol of rols) {
                let role = document.createElement('option');
                role.setAttribute("value", rol.role)
                role.text = rol.role.substring(5);
                fDelete.rolesDel.appendChild(role);
            }
            fDelete.nameDel.value = person.name;
            fDelete.emailDel.value = person.email;
            fDelete.addressDel.value = person.address;
            fDelete.ageDel.value = person.age;
        })
        let buttonDelete = document.getElementById("deleteButton");
        buttonDelete.setAttribute("onclick", "deletePerson(" + id + ")")
        modalDelete.show();
    })
}

async function deletePerson(id) {
    fetch(URL_CRUD + id, {method: 'DELETE',
        headers: {
            "Content-Type" : "application/json"
        }}).then(res => res.json());
    getTablePerson().then(r => r);
}

async function editPerson(id) {
    const formData = new FormData(formEdit);
    let personRoles = [];
    ROLES
        .then(roles => {
            let option = formEdit.roles.options;
            for(let i = 0; i < roles.length; i++) {
                    if (option[i].selected && option[i].value == roles[i].role) {
                        personRoles.push(roles[i]);
                    }
            }
            // for
            return personRoles;
        })
        .then(personRoles => {
            fetch(URL_CRUD + id, {
                method: 'PUT',
                headers: {
                    "Content-Type" : "application/json"
                },
                body: JSON.stringify({
                    "name": formData.get("name"),
                    "email": formData.get("email"),
                    "address": formData.get("address"),
                    "age": formData.get("age"),
                    "password": formData.get("password"),
                    "roles": personRoles
                })
            }).then(res => res.json());
        });
    getTablePerson().then(r => r);
}

async function getRoles() {
    let role;
     await fetch(URL_ROLES)
        .then(res => res.json())
        .then(roles => {
           role = roles;
        })
    // return Array.from(role, r => r.role);
    return role;
}


async function getPersonByID(id) {
    let person;
    await fetch(URL_CRUD + id)
        .then(resp => resp.json())
        .then(per => {
          person = per;
        })
        .catch(error => {
            alert(error.message);
        })
    return person;
}

