package shop.service;

import com.jpa.study.ch11.JpaStudyApplication;
import com.jpa.study.ch11.domain.Member;
import com.jpa.study.ch11.repository.MemberRepository;
import com.jpa.study.ch11.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JpaStudyApplication.class)
class MemberServiceTests {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;

	@Test
	public void joinTest() throws Exception {

		// given
		Member member = new Member();
		member.setUsername("Kim");

		// when
		Long saveId = memberService.join(member);
		Member findMem = memberRepository.findOne(saveId);

		// then
		assertEquals(member.getId(), findMem.getId());
		assertEquals(member.getUsername(), findMem.getUsername());
	}

	@Test
	public void duplicateUserTest() throws Exception {
		// given
		Member member1 = new Member();
		member1.setUsername("park");

		Member member2 = new Member();
		member2.setUsername("park");

		// when
		memberService.join(member1);

		// then
		Assertions.assertThrows(IllegalStateException.class, () -> {
			memberService.join(member2); // 예외 발생 해야함
		});
	}

}
