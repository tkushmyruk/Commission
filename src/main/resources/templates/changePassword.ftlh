<#import "common/common.ftlh" as com>
<@com.main>
    <div>Change your password or email</div>
    <form method="post">
    <div>
        <label>Password : <input type="password" name="password" placeholder="password" value="${password}"/></label>
    </div>
    <div>
        <label>Email : <input type="email" name="email" placeholder="email" value="${email}"/></label>
    </div>
        <button type="submit">Save</button>
    </form>
</@com.main>