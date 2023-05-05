const URL = "/auth";

getAuthPerson();

async function getAuthPerson() {
        await fetch(URL)
            .then(resp => resp.json())
            .then(user => {

                    /**Переменные в шапку*/
                    let emailForHead = document.querySelector("#emailForHead")
                    let roleOutHead = document.querySelector("#roleForHead")

                    /**Переменные в таблицу*/
                    const id = document.querySelector("#idForPerson")
                    const name = document.querySelector("#nameForPerson")
                    const email = document.querySelector("#emailForPerson")
                    const address = document.querySelector("#addressForPerson")
                    const age = document.querySelector("#ageForPerson")
                    const roleOutTable = document.querySelector("#rolesForPerson")

                    let roles = "";
                    for (let rol of user.roles) {
                            roles += rol.role.substring(5) + " ";
                    }

                    /**Вывод в шапку*/
                    emailForHead.innerHTML = user.email;
                    roleOutHead.innerHTML = roles;

                    /**Вывод в таблицу*/
                    id.innerHTML = user.id;
                    name.innerHTML = user.name;
                    email.innerHTML = user.email;
                    address.innerHTML = user.address;
                    age.innerHTML = user.age;
                    roleOutTable.innerHTML = roles;
            })
}
