<template>
    <div class="container">
        <p class="sign" align="center">SIGN IN</p>
        <div class="form1">
            <input name="username" class="un " type="text" align="center" placeholder="Username">
            <input name="password" class="pass" type="password" align="center" placeholder="Password">
            <button @click="login" class="submit" align="center">SIGN IN</button>
            <button @click="loginWithFacebook" class="facebook" align="center">LOGIN WITH FACEBOOK</button>
            <button @click="loginWithGoogle" class="google" align="center">LOGIN WITH GOOGLE+</button>
            <p class="forgot" align="center"><a href="#">Forgot Password?</a></p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'login-component',

    data() {
        return {
            username:'',
            password:''
        }
    },

    methods: {
        login: function() {
            
        },

        loginWithGoogle: function () {

        },

        loginWithFacebook: function () {

        }
    }   
}
</script>

<style scoped>
    
.container {
    background-color: #FFFFFF;
    width: 400px;
    height: 440px;
    border-radius: 1.5em;
    box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
    position: fixed;
    top: 50%;
    left: 50%;
    margin-top: -220px;
    margin-left: -200px;
}
    
.sign {
    padding-top: 40px;
    color: rgb(39, 60, 176);
    font-weight: bold;
    font-size: 23px;
}
    
.un {
    width: 76%;
    color: rgb(38, 50, 56);
    font-weight: 700;
    font-size: 14px;
    letter-spacing: 1px;
    background: rgba(136, 126, 126, 0.04);
    padding: 10px 20px;
    border: none;
    border-radius: 20px;
    outline: none;
    box-sizing: border-box;
    border: 2px solid rgba(0, 0, 0, 0.02);
    margin-bottom: 50px;
    margin-left: 46px;
    text-align: center;
    margin-bottom: 27px;
}
    
form.form1 {
    padding-top: 40px;
}
    
.pass {
    width: 76%;
    color: rgb(38, 50, 56);
    font-weight: 700;
    font-size: 14px;
    letter-spacing: 1px;
    background: rgba(136, 126, 126, 0.04);
    padding: 10px 20px;
    border: none;
    border-radius: 20px;
    outline: none;
    box-sizing: border-box;
    border: 2px solid rgba(0, 0, 0, 0.02);
    margin-bottom: 50px;
    margin-left: 46px;
    text-align: center;
    margin-bottom: 27px;
}


.un:focus, .pass:focus {
    border: 2px solid rgba(0, 0, 0, 0.18) !important;
}

.submit {
    width: 76%;
    cursor: pointer;
    border-radius: 20px;
    color: #fff;
    background: linear-gradient(to right, rgb(39, 176, 142), rgb(64, 251, 195));
    border: none;
    padding: 10px 20px;
    margin-left: 35%;
    font-size: 14px;
    outline: none;
    box-sizing: border-box;
    letter-spacing: 1px;
    box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.3);
    font-weight: 700;
    margin-bottom: 50px;
    margin-left: 46px;
    text-align: center;
    margin-bottom: 27px;
}
.facebook {
    width: 30%;
    cursor: pointer;
    border-radius: 20px;
    color: #fff;
    background: linear-gradient(to right, rgb(39, 60, 176), rgb(64, 207, 251));
    border: none;
    padding: 10px 20px;
    margin-left: 35%;
    font-size: 10px;
    outline: none;
    box-sizing: border-box;
    letter-spacing: 1px;
    box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.3);
    font-weight: 700;
    margin-bottom: 50px;
    margin-left: 46px;
    text-align: center;
    margin-bottom: 27px;
}
.google {
    width: 30%;
    cursor: pointer;
    border-radius: 20px;
    color: #fff;
    background: linear-gradient(to right, rgb(176, 39, 39), rgb(251, 64, 64));
    border: none;
    padding: 10px 20px;
    margin-left: 35%;
    font-size: 10px;
    outline: none;
    box-sizing: border-box;
    letter-spacing: 1px;
    box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.3);
    font-weight: 700;
    margin-bottom: 50px;
    margin-left: 60px;
    text-align: center;
    margin-bottom: 27px;
}

.forgot {
    text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
    color: #E1BEE7;
    padding-top: 15px;
}

a {
    text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
    color: rgb(54, 80, 196);
    text-decoration: none;
    font-weight: bold;
}
</style>
