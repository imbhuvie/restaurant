<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${item.itemId == null ? 'Add New Item' : 'Edit Item'}</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-500 flex flex-col h-screen">
    <%@include file="navbar.jsp" %>

    <div class="flex justify-center w-full mx-auto p-10">
        <div class="bg-gray-400 p-8 rounded-lg shadow-lg w-96">
            <h2 class="text-2xl font-bold mb-6 text-center">
                ${item.itemId == null ? 'Add New Item' : 'Edit Item'}
            </h2>

            <c:if test="${not empty msg}">
                <p class="text-center text-sm text-green-800">${msg}</p>
            </c:if>
            <c:if test="${not empty error}">
                <p class="text-center text-sm text-red-800">${error}</p>
            </c:if>

            <form action="addItem" method="post">
                <!-- hidden field to hold itemId if editing -->
                <c:if test="${item.itemId != null}">
                    <input type="hidden" name="itemId" value="${item.itemId}" />
                </c:if>

                <div class="mb-4">
                    <label for="itemName" class="block text-gray-700 font-semibold">Item Name</label>
                    <input type="text" id="itemName" name="itemName" value="${item.itemName}"
                        class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>
                </div>

                <div class="mb-4">
                    <label for="unit" class="block text-gray-700 font-semibold">Select Measurement Unit</label>
                    <select id="unit" name="unit" required
                        class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                        <option disabled ${item.unit == null ? 'selected' : ''}>Select Measurement Unit</option>
                        <c:forEach var="unit" items="${units}">
                            <option value="${unit.id}"
                                <c:if test="${item.unit != null && item.unit.id == unit.id}">selected</c:if>>
                                ${unit.unitName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-4">
                    <label for="itemDescription" class="block text-gray-700 font-semibold">Item Description</label>
                    <textarea id="itemDescription" name="itemDescription"
                        class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required>${item.itemDescription}</textarea>
                </div>

                <button type="submit"
                    class="w-full bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600">
                    ${item.itemId == null ? 'Add Item' : 'Update Item'}
                </button>
            </form>
        </div>
    </div>
</body>
</html>
