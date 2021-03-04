<#import "common/common.ftlh" as com>
<@com.main>
    <h2>Change your personal information</h2>
    <form method="post">
        <div><label>First Name : <input type="text" name="firstName" value="${personalInfo.getFirstName()?if_exists}"></label></div>
        <div><label>Middle Name : <input type="text" name="middleName" value="${personalInfo.getMiddleName()?if_exists}"></label></div>
        <div><label>Last Name : <input type="text" name="lastName" value="${personalInfo.getLastName()?if_exists}"></label></div>
        <div><label>Date Of birth : <input type="date" name="dateOfBirth" value="${personalInfo.getDateOfBirth()?if_exists}"></label></div>
        <div><label>Nationality : <input type="text" name="nationality" value="${personalInfo.getNationality()?if_exists}"></label></div>
        <div><p>Gender</p>
            <p><input name="gender" type="radio"
                        <#if personalInfo.getGender() == "Male"> checked="checked" </#if> value="Male">Male
            </p>
            <p><input name="gender" type="radio"
                        <#if personalInfo.getGender() == "Female"> checked="checked" </#if> value="Female">Female</p>
        </div>
        <button type="submit">Save</button>
    </form>
</@com.main>