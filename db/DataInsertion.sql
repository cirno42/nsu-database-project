insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Константинова Варвара Денисовна', 'Ангина', '', 'Парацетамол, зеленка;');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Устинова Анна Дамировна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Лазарев Дмитрий Степанович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Казакова Вероника Александровна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Чистякова Кристина Максимовна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Павлова Мария Романовна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Борисов Александр Маркович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Снегирев Владимир Александрович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Панкратов Герман Артёмович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Кузнецова Анастасия Матвеевна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Крылов Лев Тимофеевич', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Коновалов Андрей Даниилович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Ильина Мелисса Захаровна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Виноградов Фёдор Степанович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Сычев Ярослав Николаевич', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Дмитриев Илья Егорович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Яковлев Егор Артёмович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Шилов Илья Кириллович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Корнилова Мария Эриковна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Агафонова Диана Григорьевна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Медведева Вероника Макаровна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Чернышев Павел Матвеевич', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Александров Мирон Маркович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Леонтьева Екатерина Кирилловна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Соловьева Полина Руслановна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Николаева София Дмитриевна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Михайлов Степан Александрович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Лаптев Алексей Ильич', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Афанасьева Анна Артёмовна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Худяков Николай Николаевич', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Худяков Николай Николаевич', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Ильинская Маргарита Фёдоровна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Емельянова Мария Романовна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Воронин Лев Иванович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Михеев Михаил Артёмович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Макаров Иван Алексеевич', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Ковалева Аделина Дмитриевна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Селезнев Александр Миронович', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Родионова Арина Марковна', '', '', '');
insert into Patients(name, diseaseHistory, operationsHistory, medsHistory) values ('Анисимова Анна Степановна', '', '', '');


insert into DiseasesGroups(name) values ('Инфекционные');
insert into DiseasesGroups(name) values ('Стоматологические');	
insert into DiseasesGroups(name) values ('Травмы');
insert into DiseasesGroups(name) values ('Офтальмологические');
insert into DiseasesGroups(name) values ('Неврологические');

insert into Hospitals(name) values ('Городская больница №1');
insert into Hospitals(name) values ('Городская детская больница №1');
insert into Hospitals(name) values ('Городская больница №2');
insert into Hospitals(name) values ('Больница №3 имени Е.Н. Мешалкина');

insert into Polyclinics(name) values ('Городская поликлиника №1');
insert into Polyclinics(name) values ('Поликлиника имени Н.И. Пирогова');
insert into Polyclinics(name) values ('Городская поликлиника №2');
insert into Polyclinics(name) values ('Городская детская поликлиника №1');



insert into PolyclinicAttachedToHospital(hospitalId, polyclinicId) values(1,1);
insert into PolyclinicAttachedToHospital(hospitalId, polyclinicId) values(1,2);
insert into PolyclinicAttachedToHospital(hospitalId, polyclinicId) values(3,3);



insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(1, 'Инфекционное отделение', 1);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(3, 'Травматология', 1);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(4, 'Офтальмологическое отделение', 1);

insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(1, 'Инфекционное детское отделение', 2);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(2, 'Стоматологическое детское отделение', 2);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(3, 'Детская травматология', 2);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(4, 'Детская офтальмология', 2);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(5, 'Детская неврологическое отделение', 2);

insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(2, 'Стоматологическое отделение', 3);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(3, 'Травмпункт №2', 3);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(4, 'Офтальмологическое отделение', 3);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(5, 'Неврологическое отделение', 3);

insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(1, 'Инфекционное отделение больницы им. Мешалкнина', 4);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(3, 'Хирургическое отделение', 4);
insert into HospitalDepartments(diseaseGroupSpecializationId, name, hospitalId) values(4, 'Отделение здоровья глаза', 4);




insert into PolyclinicCabinets(polyclinicId, cabinetName) values(1, '1');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(1, '2');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(1, '3');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(1, '4');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(1, '5');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(1, '6');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(1, '6a');

insert into PolyclinicCabinets(polyclinicId, cabinetName) values(2, '101');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(2, '102');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(2, '103');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(2, '104');


insert into PolyclinicCabinets(polyclinicId, cabinetName) values(3, '101');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(3, '102');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(3, '103');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(3, '104');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(3, '201');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(3, '202');



insert into PolyclinicCabinets(polyclinicId, cabinetName) values(4, '101');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(4, '102');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(4, '103');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(4, '104');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(4, '105');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(4, '106');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(4, '107');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(4, '108');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(4, '109');
insert into PolyclinicCabinets(polyclinicId, cabinetName) values(4, '110');



insert into HospitalWards(wardNumber, totalPlaces, departmentId) values ('1', 6, 1);
insert into HospitalWards(wardNumber, totalPlaces, departmentId) values ('2', 6, 1);
insert into HospitalWards(wardNumber, totalPlaces, departmentId) values ('3', 6, 1);


insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1', 7, 2);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2', 7, 2);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3', 7, 2);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1', 6, 3);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2', 6, 3);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3', 6, 3);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1', 7, 4);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2', 7, 4);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3', 7, 4);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',6, 5);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2',6, 5);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3',6, 5);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',7, 6);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2',7, 6);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3',7, 6);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',6, 7);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2',6, 7);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3',6, 7);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',7, 8);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2',7, 8);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3',7, 8);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',6, 9);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2',6, 9);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3',6, 9);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',7, 10);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2',7, 10);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3',7, 10);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',6, 11);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2',6, 11);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3',6, 11);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',7, 12);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('2',7, 12);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('3',7, 12);

insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',7, 13);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',7, 14);
insert into HospitalWards(wardNumber,  totalPlaces, departmentId) values ('1',7, 15);

insert into ServiceStaffPositions(positionName, salaryCoefficient) values ('Уборщик', 1.0);
insert into ServiceStaffPositions(positionName, salaryCoefficient) values ('Санитар', 1.0);
insert into ServiceStaffPositions(positionName, salaryCoefficient) values ('Медсестра', 1.05);

insert into ServiceStaff(name) values ('Иванов Петр Сергеевич');
insert into ServiceStaff(name) values ('Иванов Сергей Сергеевич');
insert into ServiceStaff(name) values ('Петров Петр Васильевич');
insert into ServiceStaff(name) values ('Зайцев Андрей Дмитриевич');
insert into ServiceStaff(name) values ('Иванов Александр Сергеевич');
insert into ServiceStaff(name) values ('Грачев Дмитрий Игоревич');
insert into ServiceStaff(name) values ('Воробьев Семен Семенович');
insert into ServiceStaff(name) values ('Тарасов Александр Николаевич');
insert into ServiceStaff(name) values ('Иванова Мария Сергеена');
insert into ServiceStaff(name) values ('Краснова Анастасия Владимировна');
insert into ServiceStaff(name) values ('Дубова Татьяна Романовна');
insert into ServiceStaff(name) values ('Волкова Анастасия Романовна');


insert into ServiceStaff(name) values ('Лебедев Петр Сергеевич');
insert into ServiceStaff(name) values ('Козлов Сергей Сергеевич');
insert into ServiceStaff(name) values ('Новиков Петр Васильевич');
insert into ServiceStaff(name) values ('Морозов Андрей Дмитриевич');
insert into ServiceStaff(name) values ('Петров Александр Сергеевич');
insert into ServiceStaff(name) values ('Волков Дмитрий Игоревич');
insert into ServiceStaff(name) values ('Соловьев Семен Семенович');
insert into ServiceStaff(name) values ('Васильев Александр Николаевич');
insert into ServiceStaff(name) values ('Семенова Мария Сергеена');
insert into ServiceStaff(name) values ('Голубева Анастасия Владимировна');
insert into ServiceStaff(name) values ('Виноградова Татьяна Романовна');
insert into ServiceStaff(name) values ('Богданова Анастасия Романовна');

insert into ServiceStaff(name) values ('Федоров Богдан Владимирович');
insert into ServiceStaff(name) values ('Крылов Евграф Антонович');
insert into ServiceStaff(name) values ('Маслов Сергей Дмитриевич');
insert into ServiceStaff(name) values ('Федоров Богдан Владимирович');

insert into ServiceStaff(name) values ('Сидоров Вадим Георгиевич');
insert into ServiceStaff(name) values ('Лазарев Станислав Вадимович');
insert into ServiceStaff(name) values ('Егоров Матвей Максимович');
insert into ServiceStaff(name) values ('Рыбаков Аркадий Матвеевич');

insert into ServiceStaff(name) values ('Алексеева Алиса Алексеевна');
insert into ServiceStaff(name) values ('Михайлова Мария Михайловна');
insert into ServiceStaff(name) values ('Дмитриенко Алина Дмитриевна');
insert into ServiceStaff(name) values ('Федорова Арина Федоровна');


insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (1, 1, 1, 20000, '21-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (2, 1, 2, 20000, '22-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (3, 1, 3, 20000, '23-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (4, 1, 4, 20000, '24-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (5, 2, 1, 25000, '21-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (6, 2, 2, 25000, '22-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (7, 2, 3, 25000, '23-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (8, 2, 4, 25000, '24-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (9, 3, 1, 27000, '21-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (10, 3, 2, 27000, '22-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (11, 3, 3, 27000, '23-Jan-2020', NULL);
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (12, 3, 4, 27000, '24-Jan-2020', NULL);


insert into ServiceStaffWorksAtPolyclinic(workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (13, 1, 1, 20000, '21-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (14, 1, 2, 20000, '22-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (15, 1, 3, 20000, '23-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (16, 1, 4, 20000, '24-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (17, 2, 1, 25000, '21-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (18, 2, 2, 25000, '22-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (19, 2, 3, 25000, '23-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (20, 2, 4, 25000, '24-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (21, 3, 1, 27000, '21-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (22, 3, 2, 27000, '22-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (23, 3, 3, 27000, '23-Jan-2020', NULL);
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (24, 3, 4, 27000, '24-Jan-2020', NULL);

insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (25, 1, 1, 27000, '23-Jan-2020', '23-Feb-2020');
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (26, 2, 2, 27000, '24-Jan-2020', '23-Feb-2020');
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (27, 3, 3, 27000, '23-Jan-2020', '23-Feb-2020');
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (28, 1, 4, 27000, '24-Jan-2020', '23-Feb-2020');
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (29, 2, 3, 27000, '23-Jan-2020', '23-Feb-2020');
insert into ServiceStaffWorksAtHospital (workerId, positionId, hospitalId, salary, employmentDate, dismissalDate) values (30, 3, 4, 27000, '24-Jan-2020', '23-Feb-2020');

insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (31, 1, 2, 25000, '22-Jan-2020', '23-Feb-2020');
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (32, 2, 3, 25000, '23-Jan-2020', '23-Feb-2020');
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (33, 3, 4, 25000, '24-Jan-2020', '23-Feb-2020');
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (34, 1, 1, 27000, '21-Jan-2020', '23-Feb-2020');
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (35, 2, 2, 27000, '22-Jan-2020', '23-Feb-2020');
insert into ServiceStaffWorksAtPolyclinic (workerId, positionId, polyclinicId, salary, employmentDate, dismissalDate) values (36, 3, 3, 27000, '23-Jan-2020', '23-Feb-2020');

insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Васильев Артём Антонович', NULL, NULL, 1.2);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Румянцева Кира Романовна', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Митрофанов Максим Давидович', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Соловьев Ибрагим Даниилович', NULL, NULL, 1.2);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Иванова Ева Артёмовна', NULL, NULL, 1.2);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Лебедева Мила Артёмовна', NULL, NULL, 1.1);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Калашников Роберт Семёнович', NULL, NULL, 1.1);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Громова Полина Матвеевна', NULL, NULL, 1.1);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Попов Иван Захарович', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Грачева Полина Максимовна', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Миронов Всеволод Матвеевич', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Маркова Дарья Георгиевна', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Львов Алексей Михайлович', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Ткачева Дарья Михайловна', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Зверева Ева Елисеевна', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Нефедов Егор Евгеньевич', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Романов Кирилл Фёдорович', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Чистяков Никита Александрович', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Иванова Татьяна Семёновна', NULL, NULL, 1.0);
insert into Doctors(name, vacationStart, vacationEnd, salaryCoefficient) values ('Филиппова Василиса Данииловна', NULL, NULL, 1.0);

insert into CandidatsOfScience(id) values (1);
insert into CandidatsOfScience(id) values (2);
insert into CandidatsOfScience(id) values (3);
insert into CandidatsOfScience(id) values (4);


insert into DoctorsOfScience(id) values (1);
insert into DoctorsOfScience(id) values (2);
insert into DoctorsOfScience(id) values (3);

insert into Docents(id) values (4);
insert into Docents(id) values (3);


insert into Professors(id) values (1);
insert into Professors(id) values (2);

insert into DoctorsWhoCanDoOperations(id, operationsTotalCount, operationsWithLethalResult) values (1, 100, 1);	
insert into DoctorsWhoCanDoOperations(id, operationsTotalCount, operationsWithLethalResult) values (3, 90, 0);	
insert into DoctorsWhoCanDoOperations(id, operationsTotalCount, operationsWithLethalResult) values (4, 30, 0);	

insert into DoctorsWhoCanDoOperations(id, operationsTotalCount, operationsWithLethalResult) values (5, 31, 1);	
insert into DoctorsWhoCanDoOperations(id, operationsTotalCount, operationsWithLethalResult) values (6, 42, 0);	
insert into DoctorsWhoCanDoOperations(id, operationsTotalCount, operationsWithLethalResult) values (7, 38, 0);	


insert into Neuropathologists(id) values (2);
insert into Neuropathologists(id) values (8);


insert into Therapists(id) values (13);
insert into Therapists(id) values (14);

insert into Dentists(id) values (5);
insert into Dentists(id) values (6);
insert into Dentists(id) values (7);


insert into Radiologists(id) values (9);
insert into Radiologists(id) values (10);


insert into Surgeons(id) values (1);
insert into Surgeons(id) values (3);
insert into Surgeons(id) values (4);


insert into Ophthalmologists(id) values (11);
insert into Ophthalmologists(id) values (12);

insert into DocentConsultsHospital(docentId, hospitalId, salary, employmentDate, dismissalDate) values (4, 1, 50000, '15-Aug-2019', NULL);

insert into DocentConsultsPolyclinic(docentId, polyclinicId, salary, employmentDate, dismissalDate) values (3, 2, 75000, '18-Mar-2018', '27-Jun-2019');
insert into DocentConsultsPolyclinic(docentId, polyclinicId, salary, employmentDate, dismissalDate) values (3, 3, 85000, '29-Jun-2019', NULL);

insert into ProfessorConsultsHospital(professorId, hospitalId, salary, employmentDate, dismissalDate) values (1, 2, 85000, '18-Jul-2020', NULL);

insert into ProfessorConsultsPolyclinic(professorId, polyclinicId, salary, employmentDate, dismissalDate) values (2, 1, 80000, '17-Feb-2019', NULL);

insert into DoctorWorksAtHospital(doctorId, hospitalId, salary, contractStartDate, contractEndDate) values(11, 1, 60000, '13-Feb-2021', '13-Feb-2022');	
insert into DoctorWorksAtHospital(doctorId, hospitalId, salary, contractStartDate, contractEndDate) values(10, 2, 63000, '13-Jan-2021', '13-Jan-2022');
insert into DoctorWorksAtHospital(doctorId, hospitalId, salary, contractStartDate, contractEndDate) values(3, 3, 62000, '13-Feb-2018', '13-Feb-2022');
insert into DoctorWorksAtHospital(doctorId, hospitalId, salary, contractStartDate, contractEndDate) values(8, 4, 64000, '02-Mar-2017', '13-Feb-2022');
insert into DoctorWorksAtHospital(doctorId, hospitalId, salary, contractStartDate, contractEndDate) values(5, 1, 65000, '23-May-2018', '13-Feb-2022');
insert into DoctorWorksAtHospital(doctorId, hospitalId, salary, contractStartDate, contractEndDate) values(6, 2, 61000, '01-Feb-2018', '13-Feb-2022');

insert into DoctorWorksAtPolyclinic(doctorId, polyclinicId, salary, contractStartDate, contractEndDate) values (4, 1, 51000, '09-Jul-2018', '13-Feb-2022');
insert into DoctorWorksAtPolyclinic(doctorId, polyclinicId, salary, contractStartDate, contractEndDate) values (7, 2, 51000, '19-Jul-2018', '13-Feb-2022');
insert into DoctorWorksAtPolyclinic(doctorId, polyclinicId, salary, contractStartDate, contractEndDate) values (9, 3, 51400, '02-Jul-2018', '13-Feb-2022');
insert into DoctorWorksAtPolyclinic(doctorId, polyclinicId, salary, contractStartDate, contractEndDate) values (2, 4, 41000, '19-Jul-2019', '13-Feb-2022');
insert into DoctorWorksAtPolyclinic(doctorId, polyclinicId, salary, contractStartDate, contractEndDate) values (1, 3, 31000, '15-Jul-2018', '13-Feb-2022');
insert into DoctorWorksAtPolyclinic(doctorId, polyclinicId, salary, contractStartDate, contractEndDate) values (12, 2, 61000, '24-Jul-2018', '13-Feb-2022');	
insert into DoctorWorksAtPolyclinic(doctorId, polyclinicId, salary, contractStartDate, contractEndDate) values (13, 3, 71000, '15-Jul-2018', '13-Feb-2022');
insert into DoctorWorksAtPolyclinic(doctorId, polyclinicId, salary, contractStartDate, contractEndDate) values (14, 2, 61000, '24-Jul-2018', '13-Feb-2022');	

insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(18, 1, 60000, '13-Feb-2021', '13-Feb-2022');	
insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(17, 2, 63000, '13-Jan-2021', '13-Jan-2022');
insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(16, 3, 62000, '13-Feb-2018', '13-Feb-2022');
insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(15, 4, 64000, '02-Mar-2017', '13-Feb-2022');
insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(14, 1, 65000, '23-May-2018', '13-Feb-2022');
insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(13, 2, 61000, '01-Feb-2018', '13-Feb-2022');

insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (11, 1, 51000, '09-Jul-2018', '13-Feb-2022');
insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (10, 2, 51000, '19-Jul-2018', '13-Feb-2022');
insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (9, 3, 51400, '02-Jul-2018', '13-Feb-2022');
insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (8, 4, 41000, '18-Jul-2018', '13-Feb-209');
insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (7, 3, 31000, '15-Jul-2018', '13-Feb-2022');
insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (6, 2, 61000, '24-Jul-2018', '13-Feb-2022');	

insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(11, 1, 60000, '13-Feb-2020', '13-Feb-2021');	
insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(10, 2, 63000, '13-Jan-2020', '13-Jan-2021');
insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(3, 3, 62000, '13-Apr-2020', '13-Feb-2021');
insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(8, 4, 64000, '02-Mar-2016', '13-Feb-2021');
insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(5, 1, 65000, '23-May-2017', '13-Feb-2021');
insert into DoctorWorkedAtHospital(doctorId, hospitalId, salary, employmentDate, dismissalDate) values(6, 2, 61000, '01-Feb-2017', '13-Feb-2021');

insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (4, 1, 51000, '09-Jul-2017', '13-Feb-2021');
insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (7, 2, 51000, '19-Jul-2017', '13-Feb-2021');
insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (9, 3, 51400, '02-Jul-2017', '13-Feb-2021');
insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (2, 4, 41000, '18-Jul-2017', '13-Feb-2021');
insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (1, 3, 31000, '15-Jul-2017', '13-Feb-2021');
insert into DoctorWorkedAtPolyclinic(doctorId, polyclinicId, salary, employmentDate, dismissalDate) values (12, 2, 61000, '24-Jul-2017', '13-Feb-2021');	



insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (1, 2, 3, 'Замена печени', '26-Mar-2021', false);
insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (5, 1, 3, 'Удаление зуба', '26-Mar-2022', false);
insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (6, 3, 3, 'Установка брекетов', '27-Mar-2022', false);
insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (1, 2, 3, 'Замена печени', '26-Mar-2021', false);
insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (5, 4, 3, 'Удаление зуба', '26-Mar-2022', false);
insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (6, 3, 3, 'Установка брекетов', '27-Mar-2022', false);
insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (1, 2, 3, 'Замена печени', '26-Mar-2021', false);
insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (5, 6, 3, 'Удаление зуба', '26-Mar-2022', false);
insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (6, 3, 3, 'Установка брекетов', '27-Mar-2022', false);
insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (5, 6, 3, 'Удаление зуба', '26-Mar-2022', false);
insert into DoneOperationsForHospitals(doctorId, patientId, hospitalId, operationName, dateOfOperation, isResultLethal) values (6, 3, 3, 'Установка брекетов', '27-Mar-2022', false);



insert into DoneOperationsForPolyclinics(doctorId, patientId, polyclinicId, operationName, dateOfOperation, isResultLethal) values (7, 2, 3, 'Установка зубного импланта', '27-Jan-2022', false);
insert into DoneOperationsForPolyclinics(doctorId, patientId, polyclinicId, operationName, dateOfOperation, isResultLethal) values (3, 1, 3, 'Удаление аппендикса', '15-Feb-2022', false);
insert into DoneOperationsForPolyclinics(doctorId, patientId, polyclinicId, operationName, dateOfOperation, isResultLethal) values (4, 3, 3, 'Повторная замена печени', '26-Mar-2022', false);
insert into DoneOperationsForPolyclinics(doctorId, patientId, polyclinicId, operationName, dateOfOperation, isResultLethal) values (7, 2, 3, 'Установка зубного импланта', '27-Jan-2022', false);
insert into DoneOperationsForPolyclinics(doctorId, patientId, polyclinicId, operationName, dateOfOperation, isResultLethal) values (3, 4, 3, 'Удаление аппендикса', '15-Feb-2022', false);
insert into DoneOperationsForPolyclinics(doctorId, patientId, polyclinicId, operationName, dateOfOperation, isResultLethal) values (4, 3, 3, 'Повторная замена печени', '26-Mar-2022', false);
insert into DoneOperationsForPolyclinics(doctorId, patientId, polyclinicId, operationName, dateOfOperation, isResultLethal) values (7, 10, 3, 'Установка зубного импланта', '27-Jan-2022', false);
insert into DoneOperationsForPolyclinics(doctorId, patientId, polyclinicId, operationName, dateOfOperation, isResultLethal) values (3, 1, 3, 'Удаление аппендикса', '15-Feb-2022', false);
insert into DoneOperationsForPolyclinics(doctorId, patientId, polyclinicId, operationName, dateOfOperation, isResultLethal) values (4, 11, 3, 'Повторная замена печени', '26-Mar-2022', false);



insert into Laboratories(name) values('Биохимическая лаборатория №1');
insert into Laboratories(name) values('Umbrella Corp. Lab.'); 
insert into Laboratories(name) values('Городская лаборатория №1'); 
insert into Laboratories(name) values('Лаборатория физиологических исследований'); 
insert into Laboratories(name) values('Химическая лаборатория при университете');  
insert into Laboratories(name) values('Городская лаборатория широкого профиля');  
insert into Laboratories(name) values('Биохимическая лаборатория №2'); 
insert into Laboratories(name) values('Городская биологическая лаборатория'); 
insert into Laboratories(name) values('Биологическая лаборатория при медицинском колледже'); 

insert into BiologicalLaboratories(labId) values (8);
insert into BiologicalLaboratories(labId) values (2);
insert into BiologicalLaboratories(labId) values (3);
insert into BiologicalLaboratories(labId) values (9);
insert into BiologicalLaboratories(labId) values (6);


insert into BiochemicalLaboratories(labId) values (2);
insert into BiochemicalLaboratories(labId) values (7);
insert into BiochemicalLaboratories(labId) values (1);
insert into BiochemicalLaboratories(labId) values (3);


insert into PhysiologicalLaboratories(labId) values (2);
insert into PhysiologicalLaboratories(labId) values (3);
insert into PhysiologicalLaboratories(labId) values (6);
insert into PhysiologicalLaboratories(labId) values (4);

insert into TypesOfBiologicalLaboratoryServices(name) values ('Аутопсия');
insert into TypesOfBiologicalLaboratoryServices(name) values ('Биопсия');
insert into TypesOfBiologicalLaboratoryServices(name) values ('Анализ тканей органов');

insert into TypesOfBiochemicalLaboratoryServices(name) values ('Исследование крови из пальца');
insert into TypesOfBiochemicalLaboratoryServices(name) values ('Исследование венозной крови');
insert into TypesOfBiochemicalLaboratoryServices(name) values ('Исследование плазмы крови');

insert into TypesOfPhysiologicalLaboratoryServices(name) values ('Анализ объема легких');
insert into TypesOfPhysiologicalLaboratoryServices(name) values ('Общее физиологическое исследование');
insert into TypesOfPhysiologicalLaboratoryServices(name) values ('Общее обследование кровеносной системы');

insert into BiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (2, 1, 1, '21-Feb-2020');
insert into BiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (3, 2, 2, '21-Dec-2020');
insert into BiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (8, 3, 3, '03-Nov-2020');
insert into BiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (6, 1, 4, '11-Mar-2020');
insert into BiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (9, 2, 1, '21-Jan-2020');
insert into BiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (2, 3, 2, '21-Dec-2020');

insert into BiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (2, 1, 1, '19-Dec-2020');
insert into BiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (3, 2, 2, '21-Dec-2019');
insert into BiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (6, 3, 3, '21-Dec-2021');
insert into BiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (8, 1, 4, '21-Nov-2020');
insert into BiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (9, 2, 1, '21-Feb-2020');
insert into BiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (2, 3, 2, '21-Dec-2020');

insert into BiochemicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (1, 1, 2, '21-Dec-2020');
insert into BiochemicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (2, 2, 2, '21-Dec-2020');
insert into BiochemicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (3, 3, 2, '21-Dec-2020');
insert into BiochemicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (7, 1, 2, '21-Dec-2020');
insert into BiochemicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (7, 2, 2, '21-Dec-2020');
insert into BiochemicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (1, 3, 2, '21-Dec-2020');
	
insert into BiochemicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (1, 1, 2, '21-Dec-2020');
insert into BiochemicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (2, 2, 2, '21-Dec-2020');
insert into BiochemicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (3, 3, 2, '21-Dec-2020');
insert into BiochemicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (7, 1, 2, '21-Dec-2020');
insert into BiochemicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (7, 2, 2, '21-Dec-2020');
insert into BiochemicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (1, 3, 2, '21-Dec-2020');
	
insert into PhysiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (2, 1, 1, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (3, 2, 2, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (6, 3, 3, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (4, 1, 4, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (2, 2, 1, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (3, 3, 2, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesHospital(laboratoryId, serviceTypeId, hospitalId, serviceDate) values (6, 1, 3, '22-Dec-2020');
		

insert into PhysiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (2, 1, 1, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (3, 2, 2, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (6, 3, 3, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (4, 1, 4, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (2, 2, 1, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (3, 3, 2, '22-Dec-2020');
insert into PhysiologicalLaboratoryServicesPolyclinic(laboratoryId, serviceTypeId, polyclinicId, serviceDate) values (6, 1, 3, '22-Dec-2020');
		
insert into PatientTreatsInPolyclinic(patientId, doctorId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (1,1,'22-Dec-2020',NULL,'Боли в животе, аппендицит(?)',NULL,'Болеутоляющие'); 

insert into PatientTreatsInPolyclinic(patientId, doctorId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (2,2,'22-Dec-2020',NULL,'Мигрень',NULL,'Болеутоляющие');
insert into PatientTreatsInPolyclinic(patientId, doctorId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (3,4,'22-Dec-2020',NULL,'Перелом',NULL,'Болеутоляющие');
insert into PatientTreatsInPolyclinic(patientId, doctorId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (5,13,'22-Dec-2020',NULL,'ОРВИ',NULL,'Парацетамол');
insert into PatientTreatsInPolyclinic(patientId, doctorId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (6,14,'22-Dec-2020',NULL,'ОРВИ',NULL,'Парацетамол, витамины');
insert into PatientTreatsInPolyclinic(patientId, doctorId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (7,7,'22-Dec-2020',NULL,'Кариес','Чистка кариеса','Жидкости для полоскания');
insert into PatientTreatsInPolyclinic(patientId, doctorId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (8,12,'22-Dec-2020',NULL,'Жалобы на падение зрения',NULL,'Сосудорасширяющие препараты');

insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (9, 10, 1, '22-Dec-2020', NULL, 36.6, 'Радиологическое обследование в области 3.1', NULL, NULL);
insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (10, 11, 1, '22-Dec-2020', NULL, 36.6, 'Возможное отслоение сетчатки', 'Лазерное восстановление сечатки', 'Сосудорасширяющие препараты');
insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (11, 11, 1, '22-Dec-2020', NULL, 36.6, 'Близорукость', 'Коррекция зрения', 'Витамины');
insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (12, 8, 1, '22-Dec-2020', NULL, 36.8, 'Мигрень', NULL, 'Болеутоляющие, препараты от мигрени');

insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (40, 10, 1, '22-Dec-2020', '22-Jan-2020', 36.6, 'Радиологическое обследование в области 3.1', NULL, NULL);
insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (39, 11, 1, '22-Dec-2020', '22-Jan-2020', 36.6, 'Возможное отслоение сетчатки', 'Лазерное восстановление сечатки', 'Сосудорасширяющие препараты');
insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (38, 11, 1, '22-Dec-2020', '22-Jan-2020', 36.6, 'Близорукость', 'Коррекция зрения', 'Витамины');
insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (37, 8, 1, '22-Dec-2020', '22-Jan-2020', 36.8, 'Мигрень', NULL, 'Болеутоляющие, препараты от мигрени');
insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (36, 10, 1, '22-Dec-2020', '22-Jan-2020', 36.6, 'Радиологическое обследование в области 3.1', NULL, NULL);
insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (35, 11, 1, '22-Dec-2020', '22-Mar-2020', 36.6, 'Возможное отслоение сетчатки', 'Лазерное восстановление сечатки', 'Сосудорасширяющие препараты');
insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (34, 11, 1, '22-Dec-2020', '22-Mar-2020', 36.6, 'Близорукость', 'Коррекция зрения', 'Витамины');
insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (33, 8, 1, '22-Dec-2020', '22-Mar-2020', 36.8, 'Мигрень', NULL, 'Болеутоляющие, препараты от мигрени');


insert into PatientsVisitPolyclinicCabinets (cabinetId, patientId, doctorId, dateOfVisit) values (3, 20, 13, '29-Mar-2022');
insert into PatientsVisitPolyclinicCabinets (cabinetId, patientId, doctorId, dateOfVisit) values (2, 21, 14, '27-Mar-2022');
insert into PatientsVisitPolyclinicCabinets (cabinetId, patientId, doctorId, dateOfVisit) values (3, 20, 13, '26-Mar-2022');
insert into PatientsVisitPolyclinicCabinets (cabinetId, patientId, doctorId, dateOfVisit) values (2, 21, 14, '28-Mar-2022');
insert into PatientsVisitPolyclinicCabinets (cabinetId, patientId, doctorId, dateOfVisit) values (3, 22, 13, '10-Mar-2022');
insert into PatientsVisitPolyclinicCabinets (cabinetId, patientId, doctorId, dateOfVisit) values (2, 23, 14, '14-Mar-2022');

insert into PatientTreatedInPolyclinic(patientId, doctorId, polyclinicId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (2,2,1,'22-Nov-2020',NULL,'Мигрень',NULL,'Болеутоляющие');
insert into PatientTreatedInPolyclinic(patientId, doctorId, polyclinicId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (3,4,1,'22-Nov-2020',NULL,'Перелом',NULL,'Болеутоляющие');
insert into PatientTreatedInPolyclinic(patientId, doctorId, polyclinicId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (5,13,1,'22-Nov-2020',NULL,'ОРВИ',NULL,'Парацетамол');
insert into PatientTreatedInPolyclinic(patientId, doctorId, polyclinicId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (6,14,1,'22-Nov-2020',NULL,'ОРВИ',NULL,'Парацетамол, витамины');
insert into PatientTreatedInPolyclinic(patientId, doctorId, polyclinicId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (7,7,1,'22-Nov-2020',NULL,'Кариес','Чистка кариеса','Жидкости для полоскания');
insert into PatientTreatedInPolyclinic(patientId, doctorId, polyclinicId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds) values (8,12,1,'22-Nov-2020',NULL,'Жалобы на падение зрения',NULL,'Сосудорасширяющие препараты');


