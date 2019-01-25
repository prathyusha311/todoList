window.onload = function taskSummary() {

    var un = localStorage.getItem("test1");
    $.ajax({
        url: "http://localhost:8080/home",
        data: {

            "username": un

        },
        type: "POST",

        success: function (data) {
            document.getElementById("demo").innerHTML = "Hi " + un + "!!";
            var x = data.username;
            var y = data.incompleted;
            var z = data.completed;
            for (var i = 0; i < y.length; i++) {
                createTodo(y[i]);
            }
            for (var i = 0; i < z.length; i++) {
                done(z[i]);
            }
            countTodos();
        },
        error: function (e) {
            alert(e);

        }

    });


}

$("#sortable").sortable();
$("#sortable").disableSelection();

countTodos();

//create todo

$('.add-todo').on('keypress', function (e) {
    e.preventDefault
    if (e.which == 13) {
        if ($(this).val() != '') {
            var todo = $(this).val();
            createTodo(todo);
            addIncomplete(todo);
            countTodos();
        } else {
            alert("input is required");
        }
    }
});
// mark task as done
$('.todolist').on('change', '#sortable li input[type="checkbox"]', function () {
    if ($(this).prop('checked')) {
        var doneItem = $(this).parent().parent().find('label').text();
        $(this).parent().parent().parent().addClass('remove');
        done(doneItem);
        //alert(doneItem);
        addComplete(doneItem);
        countTodos();
    }
});

//delete done task from "already done"
$('.todolist').on('click', '.remove-item', function () {
    removeItem(this);
    var x = $(this).parent().text();
    //alert(x);
    taskdelete(x);
});

$('.button').on('click', function () {

    window.location.href = "index.html";
    localStorage.setItem("test1", null);


});

// count tasks
function countTodos() {
    var count = $("#sortable li").length;
    $('.count-todos').html(count);
}

//create task
function createTodo(text) {
    var markup = '<li class="ui-state-default"><div class="checkbox"><label><input type="checkbox" value="" />' + text + '</label></div></li>';
    $('#sortable').append(markup);
    $('.add-todo').val('');
}

//mark task as done
function done(doneItem) {
    var done = doneItem;
    var markup = '<li>' + done + '<button class="btn btn-default btn-xs pull-right  remove-item"><span class="glyphicon glyphicon-remove"></span></button></li>';
    $('#done-items').append(markup);
    $('.remove').remove();
}

//remove done task from list
function removeItem(element) {
    $(element).parent().remove();
}

function addIncomplete(todo) {
    var un = localStorage.getItem("test1");
    $.ajax({
        url: "http://localhost:8080/addTask",
        data: {

            "username": un,
            "task": todo

        },
        type: "POST",

        success: function (data) {
            //alert("success");

        },
        error: function (e) {
            alert(e);

        }

    });
}

function addComplete(doneItem) {
    var un = localStorage.getItem("test1");
    $.ajax({
        url: "http://localhost:8080/movetodone",
        data: {

            "username": un,
            "task": doneItem

        },
        type: "POST",

        success: function (data) {
            //alert("success");

        },
        error: function (e) {
            //alert(e);

        }

    });
}

function taskdelete(x) {
    var un = localStorage.getItem("test1");
    $.ajax({
        url: "http://localhost:8080/deletetask",
        data: {

            "username": un,
            "task": x

        },
        type: "POST",

        success: function (data) {
            //alert("success");

        },
        error: function (e) {
            alert(e);

        }

    });
}
