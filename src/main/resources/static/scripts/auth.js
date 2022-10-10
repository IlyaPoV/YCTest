const form = document.getElementById("auth__form");
form.addEventListener("submit", (evt)=>{
   evt.preventDefault();
   const formData = new FormData(evt.target);
    ajaxSend(formData).then((response)=>{
       console.log(response);
       //здесь можно сделать какой-то редирект или что-то еще
   })
});

const ajaxSend = async (formData) => {

    const response = await fetch("http://localhost:8080/authenticate", // здесь нужно добавить адрес, куда форму слать
    {
        method: "POST",
        body: formData
    });
    if (!response.ok) {
        throw new Error(`Ошибка по адресу ${url}, статус ошибки ${response.status}`);
    }
    return await response.text();
};
