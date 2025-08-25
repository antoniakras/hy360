

function sendCreateAjaxReq() {
    username = document.getElementById("username").value;
    income = document.getElementById("income").value;
    usertype = document.getElementsByTagName("h1")[0].innerHTML;

    if (usertype === "company") {
        emp_name = document.getElementById("emp_name").value;
        emp_id = document.getElementById("emp_id").value;
        console.log(emp_name);
        console.log(emp_id);
        makeAjaxReq("GET", 'CreateServlet?username=' + username + '&usertype=' + usertype + '&emp_name=' + emp_name + '&emp_id=' + emp_id + '&income=' + income, create_response);
    }
    else {
        console.log(usertype);
        console.log(income);
        console.log(username);
        makeAjaxReq("GET", 'CreateServlet?username=' + username + '&usertype=' + usertype + '&income=' + income, create_response);
    }
}

function sendDeleteAjaxReq() {
    username = document.getElementById("del_username").value;
    usertype = document.getElementsByTagName("h1")[0].innerHTML;
    console.log(username);
    console.log(usertype);
    makeAjaxReq("GET", 'DeleteServlet?username=' + username + '&usertype=' + usertype, delete_response);
}

function sendPurchaseAjaxReq() {
    username = document.getElementById("purch_username").value;
    productid = document.getElementById("p_productid").value;
    retailer = document.getElementById("p_retailer").value;
    company = document.getElementById("p_company").value;
    usertype = document.getElementsByTagName("h1")[0].innerHTML;
    console.log(username);
    console.log(productid);
    console.log(retailer);
    console.log(company);
    makeAjaxReq("GET", 'PurchaseServlet?productid=' + productid + '&retailer=' + retailer + '&username=' + username + '&usertype=' + usertype + '&company=' + company, purchase_response);
}

function sendReturnAjaxReq() {
    username = document.getElementById("return_username").value;
    productid = document.getElementById("r_productid").value;
    retailer = document.getElementById("r_retailer").value;
    company = document.getElementById("r_company").value;
    usertype = document.getElementsByTagName("h1")[0].innerHTML;
    console.log(username);
    console.log(productid);
    console.log(retailer);
    console.log(company);
    makeAjaxReq("GET", 'ReturnServlet?productid=' + productid + '&retailer=' + retailer + '&username=' + username + '&usertype=' + usertype + '&company=' + company, return_response);
}

function sendPayDebtAjaxReq() {
    username = document.getElementById("pay_debt_username").value;
    amount = document.getElementById("amount").value;
    usertype = document.getElementsByTagName("h1")[0].innerHTML;
    console.log(username);
    if (amount == '') {
        makeAjaxReq("GET", 'PayDebtServlet?username=' + username + '&usertype=' + usertype, paydebt_response);
    }
    else {
        makeAjaxReq("GET", 'PayDebtServlet?username=' + username + '&usertype=' + usertype + '&amount=' + amount, paydebt_response);

    }
}

function sendInfoAjaxReq() {
    username = document.getElementById("info_username").value;
    date = document.getElementById("date").value;
    usertype = document.getElementsByTagName("h1")[0].innerHTML;
    if (usertype == "company") {
        companyname = document.getElementById("company_name").value;
        if (username == '') {
            makeAjaxReqString("GET", 'InfoServlet?usertype=' + usertype + '&companyname=' + companyname + '&date=' + date, info_response);
        }
        else {
            makeAjaxReqString("GET", 'InfoServlet?username=' + username + '&usertype=' + usertype + '&companyname=' + companyname + '&date=' + date, info_response);
        }
    }
    else {
        console.log(username);
        console.log(date);
        if (username == '') {
            makeAjaxReqString("GET", 'InfoServlet?usertype=' + usertype + '&date=' + date, info_response);
        }
        else {
            makeAjaxReqString("GET", 'InfoServlet?username=' + username + '&usertype=' + usertype + '&date=' + date, info_response);
        }
    }
}

function retailerOTMAjaxReq() {
    makeAjaxReqString("GET", 'RetailerOTMServlet', retailerotm_response);
}


function goodUsersAjaxReq() {
    makeAjaxReqString2("GET", 'GoodUsersServlet');
}

function badUsersAjaxReq() {
    makeAjaxReqString2("GET", 'BadUsersServlet');
}

/*



TO AJAX REQUEST EDW


*/

function makeAjaxReq(http_method, url, callback) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var text = JSON.parse(this.responseText);
            callback(text);
        }
    };
    xhttp.open(http_method, url, true);
    xhttp.send();
}


function makeAjaxReqString(http_method, url, callback) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var response = this.responseText;
            callback(response);

        }
    };
    xhttp.open(http_method, url);
    xhttp.send();
}

function makeAjaxReqString2(http_method, url) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var response = this.responseText;
            console.log(response);
            document.getElementById("users").innerHTML = response;

        }
    };
    xhttp.open(http_method, url);
    xhttp.send();
}
/*  



APO EDW KAI KATW OI APANTHSEIS 



*/
function create_response(obj) {
    if (obj === true) {
        alert("The account has been succesfuly created");
    }
    else {
        alert("The account has not been created");
    }

}

function delete_response(obj) {
    if (obj === true) {
        alert("The account has been succesfuly deleted");
    }
    else {
        alert("The account has not been deleted");
    }

}

function purchase_response(obj) {

}

function return_response(obj) {

}

function paydebt_response(obj) {

}

function info_response(obj) {
    alert(obj);
}
function retailerotm_response(obj) {
    alert(obj);
}