function register(form) {

    // var eid = $('#emailId').val();
    // var name = $('#fullName'). val();
    // var mobno = $('#mobileNumber'). val();
    // var pswd = $('#password'). val()

    var eid = form.emailId.value;
    var name = form.fullName.value;
    var un = form.username.value;
    var pswd = form.password.value;


    if (eid == "" || name == "" || un == "" || pswd == "")
        alert(" * Requierd");


    else {
        $.ajax({
            url: "http://localhost:8080/add",
            data: {
                "emailId": eid,
                "fullName": name,
                "username": un,
                "password": pswd

            },
            type: "GET",

            success: function (data) {

                alert(data);
                if (data === "username already exists")
                    window.location.href = "#";
                else {
                    window.location.href = "index.html";
                }

            },
            error: function (e) {
                alert(e);

            }

        });
    }

}
