<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="w-full bg-blue-700 text-white p-4 shadow-md px-10">
    <div class="flex justify-between items-center">
        <!-- Logo -->
        <div class="text-2xl font-bold">
            <a href="/home" class="hover:text-gray-400">Restaurant</a>
        </div>

        <!-- Navigation Links -->
        <c:if test="${currentUser != null}">
            <div class="flex space-x-6">
                <a href="/unit" class="hover:font-semibold">MeasurementUnit</a>
                <a href="/add-unit" class="hover:font-semibold">Add Unit</a>
                <a href="/items" class="hover:font-semibold">Items</a>
                <a href="/add-item" class="hover:font-semibold">Add Items</a>
                <a href="/suppliers" class="hover:font-semibold">Suppliers</a>
                <a href="/add-supplier" class="hover:font-semibold">Add Suppliers</a>
                <a href="/inventories" class="hover:font-semibold">Inventories</a>
                <a href="/add-inventory" class="hover:font-semibold">Purchase Inventory</a>
                <a href="/issue-inventory" class="hover:font-semibold">Issue Inventory</a>
            </div>
        </c:if>

        <!-- Login/Register and Logout -->
        <div class="flex space-x-6 justify-center items-center">
            <c:if test="${currentUser == null}">
                <a href="/login" class="hover:text-gray-400 mx-1">Login</a>
                <a href="/register" class="hover:text-gray-400 mx-1">Register</a>
            </c:if>
            <c:if test="${currentUser != null}">
                <a href="/logout" class="bg-gray-50 p-1 px-3 text-blue-700 rounded font-semibold">Logout</a>
            </c:if>
        </div>
    </div>
</nav>
