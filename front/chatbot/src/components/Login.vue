<template>
<div onclick="$('#mensagem_alerta').hide();" style="position: fixed; top:0px; left: 200px; right: 200px;" class="btn-danger btn" id="mensagem_alerta">
   E-mail ou senha inválidos.   
   <span style="float: right;" >
       X
   </span>  
</div>

<div onclick="$('#email').val('funcionario@email.com'); $('#password').val('funcionario');" style="position: fixed; top:10px; left: 10px; height: 30px; width: 160px;" class="btn-success btn" id="mensagem_alerta">
   Preencher login   
</div>


<div class="container-login100">
    <div class="wrap-login100 p-t-20 p-b-20">
        <form class="login100-form validate-form" method="POST" action=" route('login') ">
            @csrf
            <span class="login100-form-avatar">
                <img src="images/cartorio-sp.png" alt="AVATAR">
            </span>
            <div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate = "Enter username">
                <input id="email" type="email" class="input100  $errors->has('email') ? ' is-invalid' : '' " name="email" value=" old('email') " required autofocus>
                <span class="focus-input100" data-placeholder="login"></span>
            </div>
            
            <div class="wrap-input100 validate-input m-b-50" data-validate="Enter password">
                <input id="password" type="password" class="input100  $errors->has('password') ? ' is-invalid' : '' " name="password" required>
                <span class="focus-input100" data-placeholder="Senha"></span>
            </div>

            @if ($errors->has('password'))
                <span class="invalid-feedback">
                    <strong> $errors->first('password') </strong>
                </span>
            @endif
            <div class="container-login100-form-btn">
                <button type="submit" class="login100-form-btn">
                     __('Login') 
                </button>
            </div>
        </form>
    </div>
</div>
</template>