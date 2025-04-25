<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Units</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-500 flex flex-col h-screen">
    <%@include file="navbar.jsp" %>

    <div class="flex justify-center w-full mx-auto p-10">
    <div class="bg-gray-400 p-8 rounded-lg shadow-lg w-96">
        <c:if test="${not empty msg}" >
                 <p class="text-center text-s text-green-800 " >${msg}</p>
             </c:if>
             <c:if test="${not empty error}" >
                     <p class="text-center text-s text-red-800 " >${error}</p>
             </c:if>
            <div class="container mx-auto">
                    <h2 class="text-2xl font-bold mb-4 text-center">Units List</h2>
<c:if test="${not empty ifNotUnits}" >
                                  <p class="text-center font-semibold text-s text-red-800 " >${ifNotUnits}</p>
                          </c:if>
                          <c:if test="${not empty units}">
                    <table class="w-full border-collapse border border-gray-300 bg-white shadow-lg">
                        <thead>
                            <tr class="bg-blue-500 text-white">
                                <th class="border border-gray-300 px-4 py-2">S. No.</th>
                                <th class="border border-gray-300 px-4 py-2">Unit Name</th>
                                <th class="border border-gray-300 px-4 py-2">SubUnit Name</th>
                                <th class="border border-gray-300 px-4 py-2">Unit Symbol</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% int i=1; %>
                            <c:forEach var="unit" items="${units}">
                                <tr class="hover:bg-gray-100">
                                    <td class="border border-gray-300 px-4 py-2"><%= i++ %></td>
                                    <td class="border border-gray-300 px-4 py-2">${unit.unitName}</td>
                                    <td class="border border-gray-300 px-4 py-2">${unit.subUnitName}</td>
                                    <td class="border border-gray-300 px-4 py-2">${unit.symbol}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </c:if>
                </div>
        </div>

    </div>
</body>
</html>
