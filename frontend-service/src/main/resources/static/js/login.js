// var token = "";

// const buttonLogin = document.getElementById('loginForm');
//
// buttonLogin.addEventListener('submit', async function(event) {
//     event.preventDefault();
//     console.log("inside login form");
//     var username = $('#username').val().trim();
//     var password = $('#password').val().trim();
//
//     let data;
//
//     if (username !== "" && password !== "") {
//         data = {
//             username: username,
//             password: password
//         };
//     }
//
//     let response = await fetch('http://localhost:8080/authenticate', {
//         method: 'POST',
//         cache: 'no-cache',
//         headers: {
//             'Content-Type': 'application/json'
//         },
//         body: JSON.stringify(data)
//     });
//
//     if (response.ok) {
//         response.json().then(res => {
//             console.log(res.jwt);
//             token = res.jwt;
//         });
//
//         await handleByUsername(username);
//     }
// });
//
// async function handleByUsername(username) {
//     const userResponse = await adminService.findByUsername(username);
//     const userJson = userResponse.json();
//     const roleResponse = await roleService.findAll();
//     const roleJson = roleResponse.json();
//
//     userJson.then(user => {
//         let rolesArr = []
//         user.roles.forEach(role => rolesArr.push(role.id))
//         console.log(user.firstName, user.email, rolesArr);
//
//         roleJson.then(roles => {
//             roles.forEach(role => {
//                 if (rolesArr.includes(role.id) && role.id === 1) {
//                     window.location.replace("http://localhost:8080/users/admin");
//                 } else {
//                     window.location.replace("http://localhost:8080/users/user");
//                 }
//             });
//         });
//     });
// }