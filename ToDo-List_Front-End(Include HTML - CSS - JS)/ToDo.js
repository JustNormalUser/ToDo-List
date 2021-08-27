window.i = 0;


function ekle(){
    console.log('tıklandı');
    var deger = document.getElementById('kelime_input').value;
    var li = document.createElement('li');
    var span = document.createElement("span");
    var deletebutton = document.createElement("button");
    var editbutton = document.createElement("button");
    var succ = document.createElement("input");
    li.id = "li-"+window.i;
    span.innerText = deger;
    deletebutton.innerText = "Sil";
    span.id = "span-"+window.i;
    editbutton.innerText = "Göster";
    succ.type = "checkbox";
    succ.id = "succ-"+window.i;
    
    succ.addEventListener("click",conf);
    li.appendChild(succ);
    editbutton.id= "edit-"+window.i;
    deletebutton.id = "sil-"+window.i;
    editbutton.addEventListener("click",deneme);
    deletebutton.addEventListener("click",sildir);
    li.appendChild(span);
    li.appendChild(deletebutton);
    li.appendChild(editbutton);
    document.getElementById('icerik').appendChild(li);
    window.i+=1

    document.querySelector('.ekle-popup').style.display = 'flex';
    document.querySelector('.ekleclose').addEventListener('click', function(){
    document.querySelector('.ekle-popup').style.display = 'none';
    window.location.reload(true);
    });

    document.getElementById('ekletextinput').value = deger;
    document.getElementById('EkleButton').onclick = function(){
            //Press ctrl+k and ctrl c ---> uncomment ctrl k and ctrl u
    axios({
        method: 'post',
        url: 'http://localhost:8080/todos',
        data: {
            todos: document.getElementById('ekletextinput').value,
            description: document.getElementById('ekledescriptionarea').value,
            completed: false
        }
    })
    .then(res => console.log(res))
    .catch(err => console.error(err));
    window.location.reload(true);
    };


}

function preekle(todos, id, completed){
    console.log('tıklandı');
    var deger = todos;
    var li = document.createElement('li');
    
    var span = document.createElement("span");
    var deletebutton = document.createElement("button");
    deletebutton.setAttribute('id', id);
    var editbutton = document.createElement("button");
    var succ = document.createElement("input");
    li.id = "li-"+ id;
    span.innerText = deger;
    deletebutton.innerText = "Sil";
    span.id = "span-"+window.i;
    editbutton.innerText = "Göster";
    succ.type = "checkbox";
    succ.id = id;
    
    succ.addEventListener("click",conf);
    li.appendChild(succ);
    editbutton.id= id;
    deletebutton.id = id;
    editbutton.addEventListener("click",deneme);
    deletebutton.addEventListener("click",sildir);
    li.appendChild(span);
    li.appendChild(deletebutton);
    li.appendChild(editbutton);
    document.getElementById('icerik').appendChild(li);
    window.i+=1
    preconf(id, completed);
}

async function precatch(){
    
    let url = 'http://localhost:8080/todos';
    let jsonobj = await (await fetch(url)).json();
    console.log(jsonobj); //ilerde sil
    for(var i = 0; i < jsonobj.length; i++){
        var obj = jsonobj[i];
        console.log(obj.todos, obj.id, obj.completed);
        preekle(obj.todos, obj.id, obj.completed);
    }

}



precatch();




var edit = document.getElementById("edit");

function preconf(id, completed){
    var li = document.getElementById("li-"+id);
    var succ = document.getElementById(id);
    if(completed){
        li.style="background-color:green";
        succ.checked = true;
    }else{
        li.style="background-color:transparant";
        succ.checked = false;
    }
    
}


async function getTodos(int){
    var id = int;
    const res = await axios('http://localhost:8080/todos/' +id);
    return await res.data.todos;
}

async function getDescription(int){
    var id = int;
    const res = await axios('http://localhost:8080/todos/' +id);
    return await res.data.description;
}

async function getCompleted(int){
    var id = int;
    const res = await axios('http://localhost:8080/todos/' +id);
    return await res.data.completed;
}

async function getcreatedAt(int){
    var id = int;
    const res = await axios('http://localhost:8080/todos/' +id);
    return await res.data.createdAt;
}

async function getupdatedAt(int){
    var id = int;
    const res = await axios('http://localhost:8080/todos/' +id);
    return await res.data.updatedAt;
}

var conf = async function(id){
    var id = this.id;
    var todo = await getTodos(id).then(function(int){
            return int;
    });
    var description = await getDescription(id).then(function(int){
            return int;
    });
    getcreatedAt(id);
    var succ = document.getElementById(id);
    var li = document.getElementById("li-"+id);
    if(succ.checked){
        li.style="background-color:green";
        axios({
            method: 'put',
            url: 'http://localhost:8080/todos/' +id,
            data: {
                todos : todo,
                description: description,
                completed: true
            }
        })
        .then(res => console.log(res))
        .catch(err => console.error(err));
    }else{
        li.style="background-color:transparant";
        axios({
            method: 'put',
            url: 'http://localhost:8080/todos/' +id,
            data: {
                todos: todo,
                description: description,
                completed: false
            }
        })
        .then(res => console.log(res))
        .catch(err => console.error(err));
    }
}






var deneme= async function(){
    var id = this.id;

    var todo = await getTodos(id).then(function(int){
        return int;
    });

    var aciklama = await getDescription(id).then(function(int){
        return int;
    });

    var completed = new Boolean(
        await getCompleted(id).then(function(int){
            return int;
        })
    );
    
    var createdAt = new Date(
            await getcreatedAt(id).then(function(int){
            return int;
        })
    );
    
    var updatedAtIfElse = await getupdatedAt(id).then(function(int){
        return int;
    });

    var updatedAt = new Date(
        await getupdatedAt(id).then(function(int){
        return int;
    })
    );

    if(updatedAtIfElse == null){
        var dd = String(createdAt.getDate()).padStart(2, '0');
        var mm = String(createdAt.getMonth()+ 1).padStart(2, '0');
        var yyyy = createdAt.getFullYear();
        var hour = String(createdAt.getHours() <10?'0':'') + createdAt.getHours();
        var minute = String(createdAt.getMinutes() <10?'0':'') + createdAt.getMinutes();
        var seconds = String(createdAt.getSeconds() <10?'0':'') + createdAt.getSeconds();
        createdAt = dd + '/' + mm + '/' + yyyy + ' - ' + hour +':' + minute + ':' + seconds;
        document.getElementById('tarihVEsaat').innerHTML = createdAt;
    }else{
        var dd = String(updatedAt.getDate()).padStart(2, '0');
        var mm = String(updatedAt.getMonth()+ 1).padStart(2, '0');
        var yyyy = updatedAt.getFullYear();
        var hour = String(updatedAt.getHours() <10?'0' : '') + updatedAt.getHours();
        var minute = String(updatedAt.getMinutes() <10?'0' : '') + updatedAt.getMinutes();
        var seconds = String(updatedAt.getSeconds() <10?'0' : '') + updatedAt.getSeconds();
        updatedAt = dd + '/' + mm + '/' + yyyy + ' - ' + hour +':' + minute + ':' + seconds;
        document.getElementById('tarihVEsaat').innerHTML = updatedAt;
    }

    document.querySelector('.bg-modal').style.display = 'flex';
    document.querySelector('.close').addEventListener('click', function(){
        document.querySelector('.bg-modal').style.display = 'none';
    });

    document.getElementById('textinput').value = todo;
    document.getElementById('descriptionarea').value = aciklama;
    document.getElementById('EditButton').onclick = function(){
            
        axios({
        method: 'put',
        url: 'http://localhost:8080/todos/' +id,
        data: {
            todos: document.getElementById('textinput').value,
            description: document.getElementById('descriptionarea').value,
            completed: completed
        }
    })
    .then(res => console.log(res))
    .catch(err => console.error(err));
    alert(document.getElementById('textinput').value + " güncellendi.")

    }

    document.querySelector('.close').onclick = function(){
        window.location.reload(true);
    }

}


function sildir(){
    var id = this.id;
     axios({
        method: 'delete',
        url: 'http://localhost:8080/todos/' +id,
    })
    .then(res => console.log(res))
    .catch(err => console.error(err));

    window.location.reload(true);

}

