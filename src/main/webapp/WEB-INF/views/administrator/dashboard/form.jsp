<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page import="acme.enums.Status" %>
<%@ page import="acme.entities.cookingItem.CookingItemType" %>

<style>
	.dp2-base input {
		display: block;
		margin: auto;
		text-align: center;
		background: #f9f9f9;
	}
	.dp2-base {
		text-align: center;
	}
	.dp2-base > .row {
		margin-bottom: 25px;
	}
	.dp2-base h4 {
		font-size: 1rem;
	}
</style>

<div class="row">
	<div class="col">
		<div class="dp2-base" id="totals">
			<h2><acme:message code="administrator.dashboard.form.label.totals"/></h2>
			<div class="row">
				<div class="col">
					<div class="card">
						<div class="card-body">
							<label><acme:message code="administrator.dashboard.form.label.totalingredient"/>
							<input type="text" value="${totalsData.Ingredient}" readonly />
							</label>
						</div>
					</div>
				</div>
				
				<div class="col">
					<div class="card">
						<div class="card-body">
							<label><acme:message code="administrator.dashboard.form.label.totalkitchenutensil"/>
							<input type="text" value="${totalsData.Kitchen_Utensil}" readonly />
						</label>
						</div>
					</div>
				</div>
			</div>
			
			<h2><acme:message code="administrator.dashboard.form.label.totalfinedishstatus"/></h2>
			<div class="row">
				<div class="col">
					<div class="card">
						<div class="card-body">
							<label><acme:message code="administrator.dashboard.form.label.totalNumberfinedishProposed"/>
								<input type="text" value="${totalsData.Proposed}" readonly />
							</label>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card">
						<div class="card-body">
							<label>
								<acme:message code="administrator.dashboard.form.label.totalNumberfinedishAccepted"/>
								<input type="text" value="${totalsData.Accepted}" readonly />
							</label>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card">
						<div class="card-body">
							<label>
								<acme:message code="administrator.dashboard.form.label.totalNumberfinedishDenied"/>
								<input type="text" value="${totalsData.Denied}" readonly />
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="dp2-base" id="budgets">
			<h2><acme:message code="administrator.dashboard.form.label.budgets"/></h2>
			
			<c:forEach items="${Status.values()}" var="status">
				<div class="row">
					<div class="col">
						<div class="card">
							<div class="card-body">
								<label>
									<acme:message code="administrator.dashboard.form.label.finedishBudgetsMax${status}" />
									<input type="text" value="${FineDishesBudgets[status].Max}" readonly />
								</label>
							</div>
						</div>
					</div>
					
					<div class="col">
						<div class="card">
							<div class="card-body">
								<label>
									<acme:message code="administrator.dashboard.form.label.finedishBudgetsMin${status}"/>
									<input type="text" value="${FineDishesBudgets[status].Min}" readonly />
								</label>
							</div>
						</div>
					</div>
					
					<div class="col">
						<div class="card">
							<div class="card-body">					
								<label>
									<acme:message code="administrator.dashboard.form.label.finedishBudgetsAvg${status}"/>
									<input type="text" value="${FineDishesBudgets[status].Avg}" readonly />
								</label>
							</div>
						</div>
					</div>
					
					<div class="col">
						<div class="card">
							<div class="card-body">
								<label>
									<acme:message code="administrator.dashboard.form.label.finedishBudgetsDev${status}"/>
									<input type="text" value="${FineDishesBudgets[status].Dev}" readonly />
								</label>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		
		
		<div class="dp2-base" id="items">
			<h2><acme:message code="administrator.dashboard.form.label.totals"/></h2>
			
			<c:forEach items="${CookingItemType.values()}" var="type">
				<h3>${type}</h3>
				
				<c:forEach items="${CookingItemsRetailPrice[type]}" var="data">
					<h4><acme:message code="administrator.dashboard.form.label.currency" />: ${data.key}</h4>
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-body">
									<label>
										<acme:message code="administrator.dashboard.form.label.maxPrice" />
										<input type="text" value="${data.value.Max}" readonly />
									</label>
								</div>
							</div>
						</div>
						
						<div class="col">
							<div class="card">
								<div class="card-body">
									<label>
										<acme:message code="administrator.dashboard.form.label.minPrice"/>
										<input type="text" value="${data.value.Min}" readonly />
									</label>
								</div>
							</div>
						</div>
						
						<div class="col">
							<div class="card">
								<div class="card-body">					
									<label>
										<acme:message code="administrator.dashboard.form.label.avgPrice"/>
										<input type="text" value="${data.value.Avg}" readonly />
									</label>
								</div>
							</div>
						</div>
						
						<div class="col">
							<div class="card">
								<div class="card-body">
									<label>
										<acme:message code="administrator.dashboard.form.label.devPrice"/>
										<input type="text" value="${data.value.Dev}" readonly />
									</label>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:forEach>
		</div>
		<!--Control check  -->   
		<div class="dp2-base" id="items">
		<h2><acme:message code="administrator.dashboard.pimpam.ratio"/></h2>
		<div class="row">
               <div class="col">
                  <div class="card">
                     <div class="card-body">
                        <label>
                        
                           <input type="text" value="${ratio}" readonly />
                        </label>
                     </div>
                  </div>
               </div>
               </div>
                
	 <div class="dp2-base" id="items">
		<h2><acme:message code="administrator.dashboard.pimpam.budged"/></h2>
		<c:forEach items="${pimpamBudget}" var="data">
            <h4>
               <acme:message code="patron.dashboard.form.label.currency" />
               : ${data.key}
            </h4>
            <div class="row">
               <div class="col">
                  <div class="card">
                     <div class="card-body">
                        <label>
                           <acme:message code="patron.dashboard.form.label.maxPrice" />
                           <input type="text" value="${data.value.Max}" readonly />
                        </label>
                     </div>
                  </div>
               </div>
               <div class="col">
                  <div class="card">
                     <div class="card-body">
                        <label>
                           <acme:message code="patron.dashboard.form.label.minPrice"/>
                           <input type="text" value="${data.value.Min}" readonly />
                        </label>
                     </div>
                  </div>
               </div>
               <div class="col">
                  <div class="card">
                     <div class="card-body">
                        <label>
                           <acme:message code="patron.dashboard.form.label.avgPrice"/>
                           <input type="text" value="${data.value.Avg}" readonly />
                        </label>
                     </div>
                  </div>
               </div>
               <div class="col">
                  <div class="card">
                     <div class="card-body">
                        <label>
                           <acme:message code="patron.dashboard.form.label.devPrice"/>
                           <input type="text" value="${data.value.Dev}" readonly />
                        </label>
                     </div>
                  </div>
               </div>
            </div>
         </c:forEach>
		
		</div>
	</div>
	
	<!--Fin Control check  --> 
		
		
	</div>
	
	
	<div class="col-4">
		<h2><acme:message code="administrator.dashboard.form.label.index"/></h2>
		<ul>
			<li><a href="${requestScope['javax.servlet.forward.request_uri']}#totals"><acme:message code="administrator.dashboard.form.label.totals"/></a></li>
			<li><a href="${requestScope['javax.servlet.forward.request_uri']}#budgets"><acme:message code="administrator.dashboard.form.label.budgets"/></a></li>
			<li><a href="${requestScope['javax.servlet.forward.request_uri']}#items"><acme:message code="administrator.dashboard.form.label.items"/></a></li>
		</ul>
	</div>
</div>



