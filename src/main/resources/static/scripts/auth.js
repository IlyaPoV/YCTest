// const form = document.getElementById("auth__form");
// form.addEventListener("submit", (evt) => {
//     evt.preventDefault();
//     const formData = new FormData(evt.target);
//     ajaxSend(formData).then((response) => {
//         localStorage.setItem("access_token", JSON.parse(response).access_token);
//         console.log(
//             localStorage.getItem("access_token")
//         );
//     })
// });
//
// const ajaxSend = async (formData) => {
//     const response = await fetch("/authenticate",
//         {
//             method: "POST",
//             body: formData
//         });
//     if (!response.ok) {
//         throw new Error(`Ошибка по адресу ${url}, статус ошибки ${response.status}`);
//     }
//     return await response.text();
// };
