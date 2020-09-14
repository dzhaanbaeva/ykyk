<#import "partials/main.ftlh" as main />

<@main.renderWith >
    <h3 id="user-text" style="text-align: center">Список граждан</h3>
    <div class="d-flex justify-content-end mb-3">
        <div style="margin-top: 30px; margin-bottom: 30px; margin-right: 10px">
            <a href="/add_user" ><button type="button" class="btn btn-outline-primary">Добавить гражданина</button></a>
        </div>

    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Имя</th>
            <th scope="col">Фамилия</th>
            <th scope="col">ИНН</th>
            <th scope="col">День рождения</th>
            <th scope="col">Адрес</th>
            <th scope="col">Почта</th>
            <th scope="col">Название документа</th>
            <th scope="col">Изменить</th>
            <th scope="col">Удалить</th>
        </tr>
        </thead>
        <tbody id="user-information">
        <#list users as user>
            <tr>
                <th scope="row">${user.id}</th>
                <td>${user.name}</td>
                <td>${user.fullName}</td>
                <td>${user.inn}</td>
                <td>${user.birthday}</td>
                <td>${user.address}</td>
                <td>${user.email}</td>

                <td>${user.document.typeOfDocument.name}</td>
                <td> <button class="btn btn-primary" onclick="window.location='/user/${user.id}'">Изменить</button></td>
                <td>
                    <form  method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                        </#if>
                        <input type="hidden" value="${user.id}" name="userId"/>
                        <button id="delete_${user.id}" type="button" class="btn btn-outline-primary delete"
                                data-toggle="modal" data-target="#exampleModal_${user.id}">Удалить</button>

                        <div class="modal fade" id="exampleModal_${user.id}" tabindex="-1" role="dialog"
                             aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-body">
                                        <h5 class="modal-title" id="modalForSupplier">Вы действительно хотите удалить?</h5>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" id="deleteSupplierButton_${user.id}" class="btn btn-outline-primary"
                                                onclick="deleteUser(this.form)" data-dismiss="model">Ок
                                        </button>
                                        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Назад
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "partials/pagination.ftlh">
</@main.renderWith>