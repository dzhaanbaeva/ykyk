<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h3 class="h2_for_css">Изменение данных гражданина</h3>
    <table class="table mt-3 mb-5">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Имя</th>
            <th scope="col">Фамилия</th>
            <th scope="col">ИНН</th>
            <th scope="col">День рождения</th>
            <th scope="col">Адрес</th>
            <th scope="col">Почта</th>
            <th scope="col">Тип документ</th>
        </tr>
        </thead>
        <tbody>
        <tr class="pointer">
            <th>${user.id}</th>
            <td>${user.name}</td>
            <td>${user.fullName}</td>
            <td>${user.inn}</td>
            <td>${user.birthday}</td>
            <td>${user.address}</td>
            <td>${user.email}</td>

            <td>${user.document.typeOfDocument.getName()}</td>
        </tr>
        </tbody>
    </table>

    <hr>

    <form action="/update" method="post" class="mx-auto updateUser">
        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-row">
            <div class="col-md-4 mb-3">
                <label>Введите имя</label>
                <input type="text" name="name" class="form-control border border-primary"
                       placeholder="Имя"
                       value="" autofocus required/>
            </div>
            <div class="col-md-4 mb-3">
                <label>Введите фамилию</label>
                <input type="text" name="fullName" class="form-control border border-primary"
                       placeholder="Фамилия"
                       value="" autofocus required/>
            </div>
            <div class="col-md-4 mb-3">
                <label>Введите инн</label>
                <input type="number" name="inn" class="form-control border border-primary"
                       placeholder="ИНН"
                       value="" autofocus required/>
            </div>
            <div class="col-md-4 mb-3">
                <label>Введите день рождение</label>
                <input type="date" name="birthday" class="form-control border border-primary"
                       placeholder="dd-mm-yyyy"
                       value="" autofocus required/>
            </div>
            <div class="col-md-4 mb-3">
                <label>Введите адрес</label>
                <input type="text" name="address" class="form-control border border-primary"
                       placeholder="Адрес"
                       value="" autofocus required/>
            </div>
            <div class="col-md-4 mb-3">
                <label>Введите адрес эл.почты</label>
                <input type="text" name="email" class="form-control border border-primary"
                       placeholder="Адрес эл.почта"
                       value="" autofocus required/>
            </div>
            <div class="col-md-4 mb-3">
                <label class="my-1 mr-2">Введите название документа:</label>
                <select class="custom-select my-1 mr-sm-2  border border-primary" name="document" autofocus
                        required>
                    <option hidden selected>${user.document.typeOfDocument.name}</option>
                    <#list document as type>
                        <option value="${type.id}">${type.name}</option>
                    </#list>
                </select>
                <button class="btn btn-primary mt-3" type="submit">Обновить</button>
            </div>
        </div>
    </form>

</@main.renderWith>