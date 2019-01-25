function login(form) {

    var un = form.username.value;
    var pswd = form.password.value;

    if (un == "" || pswd == "")
        alert(" * Required");
    else {
        $.ajax({
            url: "http://localhost:8080/login",
            data: {

                "username": un,
                "password": pswd

            },
            type: "POST",

            success: function (data) {
                alert(data);
                var x = un;
                if (data === "success") {
                    localStorage.setItem("test1", x);
                    window.location.href = "todo.html";
                }
                else
                    window.location.href = "index.html";
            },
            error: function (e) {
                alert("failed");

            }

        });
    }


}





