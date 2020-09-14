<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h2 class="h2_for_css">Sign in</h2>
    <div class="d-flex justify-content-center">
        <div class="mx-3">
            <form action="/login" method="post">
                <#if error!false >
                    <p style="color:red">Invalid username or password !</p>
                </#if>
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                </#if>
                <div>
                    <label class="flex flex-v-center ">
                        <span class="label-input-text">Email:</span>
                        <input class="form-control mb-2" type="email" name="username"  value="${(dto.email)!''}" autofocus >
                    </label>
                </div>
                <div>
                    <label class="flex flex-v-center label-input-text">
                        <span class="label-input-text">Password:</span>
                        <input type="password" class="form-control mb-2" name="password" value="">
                    </label>
                </div>
                <div>
                    <button class="btn btn-outline-primary btn-block"type="submit">Sign in!</button>
                </div>
            </form>
        </div>
    </div>
</@main.renderWith>