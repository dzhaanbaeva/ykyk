<#import "partials/main.ftlh" as main />

<@main.renderWith>

    <#if user??>
         <#if  roles == "[USER]">
        <div class="jumbotron jumbotron-fluid">
            <div class="container">
                <h1 class="h2_for_css">Ваши данные!</h1>
                <p class="user_css">Имя: ${user.getName()}</p>
                <p class="user_css">Фамилия: ${user.getFullName()}</p>
                <p class="user_css">ИНН: ${user.getInn()}</p>
                <p class="user_css">Дата рождения: ${user.getBirthday()}</p>
                <p class="user_css">Адрес: ${user.getAddress()}</p>
                <p class="user_css">Email: ${user.getEmail()}</p>
                <p class="index-content"></p>
            </div>
        </div>
        <#elseif roles == "[ADMIN]">
         <h1 class="h2_for_css">Добро пожаловать ${user.getFullName()}</h1>
         </#if>
    <#else>
    <p class="h2_for_css">Войдите в систему!</p>
    </#if>


</@main.renderWith>