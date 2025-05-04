<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add/Update Measurement Unit</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-500 flex flex-col h-screen">
<%@include file="navbar.jsp" %>

<div class="flex justify-center w-full mx-auto p-10">
    <div class="bg-gray-400 p-8 rounded-lg shadow-lg w-96">
        <h2 class="text-2xl font-bold mb-6 text-center">
            <c:choose>
                <c:when test="${not empty measurementUnit.id}">
                    Edit Measurement Unit
                </c:when>
                <c:otherwise>
                    Add New Measurement Unit
                </c:otherwise>
            </c:choose>
        </h2>

        <c:if test="${not empty msg}">
            <p class="text-center text-green-700">${msg}</p>
        </c:if>
        <c:if test="${not empty error}">
            <p class="text-center text-red-700">${error}</p>
        </c:if>

        <form action="addUnit" method="post">
            <!-- Hidden ID field (used for edit only) -->
            <input type="hidden" name="id" value="${measurementUnit.id}"/>

            <div class="mb-4">
                <label class="block text-gray-700 font-semibold" for="unitName">Unit Name</label>
                <input type="text" id="unitName" name="unitName" value="${measurementUnit.unitName}"
                       class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" required/>
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 font-semibold" for="subUnitName">Sub Unit Name</label>
                <input type="text" id="subUnitName" name="subUnitName" value="${measurementUnit.subUnitName}"
                       class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/>
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 font-semibold" for="symbol">Symbol</label>
                <input type="text" id="symbol" name="symbol" value="${measurementUnit.symbol}"
                       class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"/>
            </div>

            <button type="submit"
                    class="w-full bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600">
                <c:choose>
                    <c:when test="${not empty measurementUnit.id}">
                        Update Unit
                    </c:when>
                    <c:otherwise>
                        Add Unit
                    </c:otherwise>
                </c:choose>
            </button>
        </form>
    </div>
</div>
</body>
<script>
    function checkValidation() {
            const alphaRegex = /^[A-Za-z]+$/;

            const unitName = document.getElementById("unitName").value.trim();
            const subUnitName = document.getElementById("subUnitName").value.trim();
            const symbol = document.getElementById("symbol").value.trim();

            if (!alphaRegex.test(unitName)) {
                alert("Unit Name must contain only alphabets without spaces.");
                return false;
            }

            if (!alphaRegex.test(symbol)) {
                alert("Unit Symbol must contain only alphabets without spaces.");
                return false;
            }
            return true;
        }

</script>
</html>
