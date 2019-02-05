DO
$$DECLARE 
   companyId integer := 1; 
   branchCount integer := 0; 
   branchId integer := 1; 
   employeeCount integer := 0;
   employeeId integer := 1;
BEGIN

   WHILE companyId <= 10 LOOP
      branchCount := 0;
      insert into company values(companyId, 'Empresa ' || companyId, 'Descrição da empresa ' || companyId);
	
      WHILE branchCount < 10 LOOP
         employeeCount := 0;
         insert into branch values (branchId, 'Filial ' || (branchCount + 1), companyId);
         insert into address values (branchId, '', 'Rua da filial ' || (branchCount + 1), branchId);

         WHILE employeeCount <= 10 LOOP
            insert into employee values (employeeId, 'Empregado ' || (employeeCount + 1), branchId);
            employeeCount := employeeCount + 1;
            employeeId := employeeId + 1;
         END LOOP;
	 
         branchCount := branchCount + 1;
         branchId := branchId + 1;
      END LOOP;
      
      companyId := companyId + 1;
   END LOOP;
    
END$$;
