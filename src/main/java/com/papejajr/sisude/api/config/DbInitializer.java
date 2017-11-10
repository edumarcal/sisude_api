//Agradeço a DEUS pelo dom do conhecimento

package com.papejajr.sisude.api.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.papejajr.sisude.api.model.Clinic;
import com.papejajr.sisude.api.model.ClinicExam;
import com.papejajr.sisude.api.model.Doctor;
import com.papejajr.sisude.api.model.Exam;
import com.papejajr.sisude.api.model.User;
import com.papejajr.sisude.api.model.Request;
import com.papejajr.sisude.api.persistence.ClinicExamRepository;
import com.papejajr.sisude.api.persistence.ClinicRepository;
import com.papejajr.sisude.api.persistence.DoctorRepository;
import com.papejajr.sisude.api.persistence.ExamRepository;
import com.papejajr.sisude.api.persistence.RequestRepository;
import com.papejajr.sisude.api.persistence.UserRepository;


@Component
public class DbInitializer implements CommandLineRunner {

	// Repositories
	@Autowired ClinicExamRepository ClinicExamRepository;
	@Autowired ClinicRepository ClinicRepository;
	@Autowired DoctorRepository DoctorRepository;
	@Autowired ExamRepository ExamRepository;
	@Autowired RequestRepository RequestRepository;
	@Autowired UserRepository UserRepository;
	

	@Override
	public void run(String... arg0) throws Exception {
		// Add User
		List<User> Users = Arrays.asList(
				new User("Luiz", "luiz", "password"),
				new User("Eduardo", "eduardo", "password"),
				new User("João Paulo", "eduardo", "password")
		);
		UserRepository.save(Users);
		
		// Add Exam
		List<Exam> Exams = Arrays.asList(
				new Exam("Angiologia"),
				new Exam("Cardiologia"),
				new Exam("Clínico Geral"),
				new Exam("Dermatologia"),
				new Exam("Ecocardiograma"),
				new Exam("Eletrocardiograma"),
				new Exam("Endocrinologia"),
				new Exam("Fonoaudiologia"),
				new Exam("Gastroenterologia"),
				new Exam("Ginecologia"),
				new Exam("Neurologia"),
				new Exam("Nutrição"),
				new Exam("Oftalmologia"),
				new Exam("Medicina do Trabalho"),
				new Exam("Otorrinolaringologia"),
				new Exam("Ortopedia"),
				new Exam("Pediatria"),
				new Exam("Psicologia"),
				new Exam("Urologia"),
				new Exam("Ultrassonografia")
		);
		ExamRepository.save(Exams);

		// Add Doctor
		List<Doctor> Doctors = Arrays.asList(
				new Doctor("Dr. Marçal", "CRM 1234 RN"),
				new Doctor("Dr. Cláudio", "CRM 456 RJ"),
				new Doctor("Dra. Ana", "CRM 789 SP"),
				new Doctor("Dra. Vitória", "CRM 159 AM"),
				new Doctor("Dra. Maria Clara", "CRM 753 SE")
		);
		DoctorRepository.save(Doctors);
		
		// Add Clinic
		List<Clinic> Clinics = Arrays.asList(
				new Clinic("Saude TADS", "Av. Luiz lopes Varela", "(84) 9 9163-2045"),
				new Clinic("Sisaude","Midwall Mall","(84) 1 2345-7896"),
				new Clinic("Saude","Bairro Nordeste","(84) 2 3232-3232"),
				new Clinic("Especilizada em pediatria","Zona Norte","(84) 5 1234-6578"),
				new Clinic("Saude em planos","Zona Sul","(85) 7 9876-4123")
		);
		ClinicRepository.save(Clinics);

		// Add ClinicExam
		List<ClinicExam> clinicExam = Arrays.asList(
				new ClinicExam(ClinicRepository.findByName("Saude TADS"), ExamRepository.findByDescription("Pediatria"),DoctorRepository.findByName("Dr. Marçal")),
				new ClinicExam(ClinicRepository.findByName("Sisaude"), ExamRepository.findByDescription("Psicologia"),DoctorRepository.findByName("Dra. Maria Clara")),
				new ClinicExam(ClinicRepository.findByName("Saude em planos"), ExamRepository.findByDescription("Otorrinolaringologia"),DoctorRepository.findByName("Dr. Cláudio"))
		);
		ClinicExamRepository.save(clinicExam);

		// Add Request
		//User user, Clinic clinic, Exam exam, Doctor doctor, String schedule
		List<Request> Requests = Arrays.asList(
				new Request(UserRepository.findByName("João Paulo") ,ClinicRepository.findByName("Saude TADS"), ExamRepository.findByDescription("Pediatria"),DoctorRepository.findByName("Dr. Marçal"), "12:00"),
				new Request(UserRepository.findByName("Luiz") ,ClinicRepository.findByName("Sisaude"), ExamRepository.findByDescription("Psicologia"),DoctorRepository.findByName("Dra. Maria Clara"), "14:00"),
				new Request(UserRepository.findByName("Eduardo") ,ClinicRepository.findByName("Saude em planos"), ExamRepository.findByDescription("Otorrinolaringologia"),DoctorRepository.findByName("Dr. Cláudio"), "22:00")
		);
		RequestRepository.save(Requests);

	}

}
