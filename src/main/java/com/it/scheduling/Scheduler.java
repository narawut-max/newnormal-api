package com.it.scheduling;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.it.controller.SendEmailController;
import com.it.entity.BookingEntity;
import com.it.entity.ProvinceEntity;
import com.it.entity.UserEntity;
import com.it.enums.MailFlags;
import com.it.model.BookingResponse;
import com.it.model.UserResponse;
import com.it.repository.BookingRepository;
import com.it.repository.UserRepository;
import com.it.utils.SendEmailUtils;

@Component
public class Scheduler {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private SendEmailUtils sendEmailUtils;

	@Autowired
	private UserRepository userRepository;

	private static final int timeForSentMail = 15;
	private static final String mailSubject = "จะถึงคิวของคุณในอีก 15 นาที";
	private static final String mailSubject2 = "จะถึงคิวของคุณในอีก 10 นาที";
	private static final String mailDetail = 
			"<b>เรียน</b> &nbsp;&nbsp;ผู้ใช้บริการจองคิวเข้ารักษาโรค"
			+ "<br>"
			+ "<b>เรื่อง</b> &nbsp;&nbsp;แจ้งเวลาเข้ารักษาของท่าน"
			+ "<br><br>"
			+ "ตามที่ท่านได้จองคิวเข้ารักษา ทางโรงพยาบาลขอแจ้งให้ทราบว่าอีก 15 นาที จะถึงคิวการรักษาของท่าน"
			+ "<br>"
			+ "ขอให้ท่านไปรอที่แผนกการรักษาก่อนถึงเวลานัดอย่างน้อย <b>5 นาที</b> เพื่อความตรงต่อเวลาและลดปัญหาที่จะตามมา"
			+ "<br>"
			+ "โปรดเตรียมบัตรประชาชนและหลักฐานการจองคิวจากระบบเพื่อแสดงข้อมูลการจองคิวต่อเจ้าหน้าที่โรงพยาบาล"
			+ "<br>"
			+ "หากท่านมาไม่ทันตามเวลาที่จอง ทางโรงพยาบาล<b>ขออนุญาตข้ามคิว</b> และ <b>ยกเลิกคิว</b> หากไม่มาภายใน 1 ชั่วโมง"
			+ "<br><br>"
			+ "ขอแสดงความนับถือ"
			+ "<br>"
			+ "โรงพยาบาลนวเวช";
	

	@Scheduled(fixedRate = 1000)
	public void sendmailScheduler() {
		System.out.println("start Scheduler send mail 15 minutes");
		List<BookingEntity> entities = bookingRepository.findAll();
		if (CollectionUtils.isNotEmpty(entities)) {
			LocalDate currentDate = LocalDate.now();
			for (BookingEntity entity : entities) {
				// convert bkDate to date for compare current date to process
				LocalDate bkDate = LocalDate.parse(entity.getBkDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				if (currentDate.compareTo(bkDate) == 0) {
					DateTimeFormatter bkTime = DateTimeFormatter.ofPattern("HH:mm");
					LocalTime currentTime = LocalTime.parse(LocalTime.now().format(bkTime), bkTime);
					LocalTime timeForCompare = LocalTime.parse(entity.getBkTime(), bkTime);
					Duration duration = Duration.between(currentTime, timeForCompare);
					
					//LocalTime bkTime = LocalTime.parse(entity.getBkTime(), DateTimeFormatter.ofPattern("HH:mm"));
					//Duration duration = Duration.between(bkTime, LocalTime.now());
					if (duration.toMinutes() == 15) {
						Optional<UserEntity> user = userRepository.findById(Integer.parseInt(entity.getUserId()));
						if (user.isPresent() && !MailFlags.SENDTED.value.equals(entity.getMailFlag())) {
							sendEmailUtils.sendSimpleMessage(user.get().getUserEmail(), mailSubject, mailDetail);
							entity.setMailFlag(MailFlags.SENDTED.value);
							bookingRepository.save(entity);
							System.out.println("send mail to " + user.get().getUserEmail() + " success");
						}
					}
					
					if (duration.toMinutes() == 10) {
						Optional<UserEntity> user = userRepository.findById(Integer.parseInt(entity.getUserId()));
						if (user.isPresent() && !MailFlags.SENDTED.value.equals(entity.getMailFlag())) {
							sendEmailUtils.sendSimpleMessage(user.get().getUserEmail(), mailSubject2, mailDetail);
							entity.setMailFlag(MailFlags.SENDTED.value);
							bookingRepository.save(entity);
							System.out.println("send mail to " + user.get().getUserEmail() + " success");
						}
					}
				}
			}
		}
	}
}
