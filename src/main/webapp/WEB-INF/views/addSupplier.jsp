<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <c:choose>
            <c:when test="${not empty supplier.supplierId}">
                Edit Supplier
            </c:when>
            <c:otherwise>
                Add New Supplier
            </c:otherwise>
        </c:choose>
    </title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-500 flex flex-col h-screen">
<%@include file="navbar.jsp" %>

<div class="flex justify-center w-full mx-auto p-10">
    <div class="bg-gray-400 p-8 rounded-lg shadow-lg w-96">
        <h2 class="text-2xl font-bold mb-6 text-center">
            <c:choose>
                <c:when test="${not empty supplier.supplierId}">
                    Edit Supplier
                </c:when>
                <c:otherwise>
                    Add New Supplier
                </c:otherwise>
            </c:choose>
        </h2>

        <c:if test="${not empty msg}">
            <p id="message" class="text-center text-sm text-green-800">${msg}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p id="message" class="text-center text-sm text-red-800">${error}</p>
        </c:if>

        <form action="addSupplier" method="post">
            <input type="hidden" name="supplierId" value="${supplier.supplierId}" />

            <div class="mb-4">
                <label for="supplierName" class="block text-gray-700 font-semibold">Firm Name</label>
                <input type="text" id="supplierName" name="supplierName" value="${supplier.supplierName}" required
                       class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>

            <div class="mb-4">
                <label for="supplierContact" class="block text-gray-700 font-semibold">Firm Contact</label>
                <input type="text" id="supplierContact" name="supplierContact" value="${supplier.supplierContact}" required
                       class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>

            <div class="mb-4">
                <label for="supplierPersonName" class="block text-gray-700 font-semibold">Contact Person Name</label>
                <input type="text" id="supplierPersonName" name="supplierPersonName" value="${supplier.supplierPersonName}" required
                       class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>

            <div class="mb-4">
                <label for="supplierPersonContact" class="block text-gray-700 font-semibold">Contact Person Mobile</label>
                <input type="text" id="supplierPersonContact" name="supplierPersonContact" value="${supplier.supplierPersonContact}" required
                       class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>

            <div class="mb-4">
                <label for="supplierAddress" class="block text-gray-700 font-semibold">Firm Address</label>
                <textarea id="supplierAddress" name="supplierAddress" required
                          class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">${supplier.supplierAddress}</textarea>
            </div>

            <button type="submit"
                    class="w-full bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600">
                <c:choose>
                    <c:when test="${not empty supplier.supplierId}">
                        Update Supplier
                    </c:when>
                    <c:otherwise>
                        Add Supplier
                    </c:otherwise>
                </c:choose>
            </button>
        </form>
    </div>
</div>
</body>
</html>
