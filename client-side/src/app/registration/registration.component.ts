import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent {
  registrationForm: FormGroup;
  submitted = false;

  passwordRequirements = {
    length: { valid: false, text: 'Ít nhất 8 ký tự' },
    lowercase: { valid: false, text: 'Chứa chữ cái thường (a-z)' },
    uppercase: { valid: false, text: 'Chứa chữ cái hoa (A-Z)' },
    number: { valid: false, text: 'Chứa số (0-9)' },
    special: { valid: false, text: 'Chứa ký tự đặc biệt (!@#$%^&*)' },
    noRepeat: { valid: false, text: 'Không quá 2 ký tự giống nhau liên tiếp' },
    threeTypes: { valid: false, text: 'Thỏa mãn ít nhất 3 loại ký tự' },
  };

  constructor(private formBuilder: FormBuilder) {
    this.registrationForm = this.formBuilder.group(
      {
        username: ['', [Validators.required]],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required]],
        confirmPassword: ['', [Validators.required]],
      },
      {
        validators: this.checkPasswordsMatch,
      }
    );

    this.registrationForm
      .get('password')
      ?.valueChanges.subscribe((password: string) => {
        if (password) {
          this.checkPasswordRequirements(password);
        }
      });
  }

  checkPasswordRequirements(password: string): void {
    this.passwordRequirements.length.valid = password.length >= 8;

    this.passwordRequirements.lowercase.valid = /[a-z]/.test(password);

    this.passwordRequirements.uppercase.valid = /[A-Z]/.test(password);

    this.passwordRequirements.number.valid = /[0-9]/.test(password);

    this.passwordRequirements.special.valid = /[!@#$%^&*]/.test(password);

    this.passwordRequirements.noRepeat.valid = !/(.)\1{2,}/.test(password);

    let typesCount = 0;
    if (this.passwordRequirements.lowercase.valid) typesCount++;
    if (this.passwordRequirements.uppercase.valid) typesCount++;
    if (this.passwordRequirements.number.valid) typesCount++;
    if (this.passwordRequirements.special.valid) typesCount++;

    // Kiểm tra ít nhất 3 loại ký tự
    this.passwordRequirements.threeTypes.valid = typesCount >= 3;
  }

  // Validator để kiểm tra mật khẩu và xác nhận mật khẩu
  checkPasswordsMatch = (group: AbstractControl): ValidationErrors | null => {
    const passwordControl = group.get('password');
    const confirmPasswordControl = group.get('confirmPassword');

    if (!passwordControl || !confirmPasswordControl) {
      return null;
    }

    if (
      confirmPasswordControl.value &&
      passwordControl.value !== confirmPasswordControl.value
    ) {
      confirmPasswordControl.setErrors({ notSame: true });
      return { notSame: true };
    } else {
      // Chỉ xóa lỗi 'notSame', giữ nguyên các lỗi khác nếu có
      const currentErrors = confirmPasswordControl.errors;
      if (currentErrors) {
        const { notSame, ...otherErrors } = currentErrors;
        confirmPasswordControl.setErrors(
          Object.keys(otherErrors).length ? otherErrors : null
        );
      }
      return null;
    }
  };

  // Xử lý khi form được submit
  onSubmit(): void {
    this.submitted = true;

    // Kiểm tra form hợp lệ
    if (this.registrationForm.invalid) {
      return;
    }

    // Kiểm tra password có đáp ứng đủ điều kiện không
    const allRequirementsMet = Object.values(this.passwordRequirements).every(
      (requirement) => requirement.valid
    );

    if (!allRequirementsMet) {
      alert('Vui lòng đảm bảo mật khẩu đáp ứng tất cả các yêu cầu!');
      return;
    }

    // Nếu tất cả điều kiện được đáp ứng, xử lý đăng ký
    alert('Đăng ký thành công!');
    console.log(this.registrationForm.value);
  }

  // Getter để dễ dàng truy cập form controls trong template
  get formControls() {
    return this.registrationForm.controls;
  }
}
