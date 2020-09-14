<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h3 class="mb-3 add-title" style="text-align: center">Добавление гражданина</h3>
    <div class="add-content">
        <form action="/add_user" method="post">
            <div class="add-root">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                           value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">Имя:</span>
                        <input class="form-control mb-2" placeholder="Имя" name="name" required/>
                    </label>
                </div>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">Фамилия:</span>
                        <input class="form-control mb-2" placeholder="Фамилия" name="fullName" required/>
                    </label>
                </div>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">ИНН:</span>
                        <input
                                class="form-control mb-2"
                                placeholder="ИНН"
                                name="inn"
                                min="14"
                                type="number"
                                pattern="/^-?\d+\.?\d*$/" onKeyPress="if(this.value.length===14) return false;"
                                required/>
                    </label>
                </div>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">День рождения:</span>
                        <input type="date" class="form-control mb-2" placeholder="День рождения" name="birthday"
                               max="${date}"  required/>
                    </label>
                </div>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">Адрес:</span>
                        <input class="form-control mb-2" placeholder="Адрес" name="address" required/>
                    </label>
                </div>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">Почта:</span>
                        <input class="form-control mb-2" placeholder="email" name="email" required/>
                    </label>
                </div>
                <div>
                    <label class="flex flex-v-center ">Документ</label>
                    <select class="custom-select mb-3" name="document" required>
                        <#list documents as document>
                            <option value="${document.id}">${document.name}</option>
                        </#list>
                    </select>
                </div>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">Номер документа:</span>
                        <input type="number" class="form-control mb-2" placeholder="Номер документа" name="numberOfDoc" min="1" required/>
                    </label>
                </div>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">Кем выдан:</span>
                        <input class="form-control mb-2" placeholder="Кем выдан" name="issuedByWhom" required/>
                    </label>
                </div>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">Дата выдачи:</span>
                        <input type="date" class="form-control mb-2" placeholder="Дата выдачи" name="dateOfIssue"
                              max="${date}" required/>
                    </label>
                </div>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">Срок истечения:</span>
                        <input type="date" class="form-control mb-2" placeholder="Срок истечения" name="expirationDate"
                               min="${date}"  required/>
                    </label>
                </div>

                <div>
                </div>
            </div>
            <button class="btn btn-outline-primary add-form-btn" type="submit">Добавить гражданина</button>
        </form>
    </div>
</@main.renderWith>