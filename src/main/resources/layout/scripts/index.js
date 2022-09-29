const testForm = document.getElementById("testForm");
const modal = document.querySelector('.test__modal');
const closeBtn = document.querySelector('.modal__negative-btn');
const sendFormBtn = document.querySelector('.modal__negative-btn');

testForm.addEventListener('submit', (evt)=>{
    evt.preventDefault();
    modal.classList.add('test__modal--open');
})

closeBtn?.addEventListener('click', (evt)=>{
    evt.preventDefault();
    modal.classList.remove('test__modal--open');
})

sendFormBtn?.addEventListener('click', (evt)=>{
    evt.preventDefault();
    const formData = new FormData(testForm);
    ajaxSend(formData)
        .then((response)=>{
            console.log(response);
            //здесь можно сделать какой-то редирект или что-то еще
        })
})

const ajaxSend = async (formData) => {
    const response = await fetch("", // здесь нужно добавить адрес, куда форму слать 
    {
        method: "POST",
        body: formData
    });
    if (!response.ok) {
        throw new Error(`Ошибка по адресу ${url}, статус ошибки ${response.status}`);
    }
    return await response.text();
};
