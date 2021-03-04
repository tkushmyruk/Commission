<#import "common/common.ftlh" as com>
<@com.main>
    <h2>${facultyName}</h2>
    <div>Change Address Information</div>
    <form method="post">
        <div><label>City : <input type="text" name="city" value="${addressInfo.getCity()?if_exists}"></label></div>
        <div><label>Street : <input type="text" name="street" value="${addressInfo.getStreet()?if_exists}"></label></div>
        <div><label>Building : <input type="text" name="building" value="${addressInfo.getBuilding()?if_exists}"></label></div>
        <div><label>Apartment : <input type="text" name="apartment" value="${addressInfo.getApartment()?if_exists}"></label></div>
        <div><label>PostCode : <input type="text" name="postCode" value="${addressInfo.getPostCode()?if_exists}"></label></div>
        <button type="submit">Save</button>
    </form>
</@com.main>