package scisrc.mobiledev.ecommercelayout.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import scisrc.mobiledev.ecommercelayout.databinding.FragmentProfileBinding
import scisrc.mobiledev.ecommercelayout.ui.models.User
import scisrc.mobiledev.ecommercelayout.utils.ProfileManager

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var profileImageUri: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadProfile()

        // 🔹 คลิกปุ่มบันทึกข้อมูล
        binding.saveProfileButton.setOnClickListener {
            saveProfile()
        }

        // 🔹 คลิกเปลี่ยนรูปโปรไฟล์
        binding.changeProfileImageButton.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun loadProfile() {
        val user = ProfileManager.getUser(requireContext())
        binding.profileImageView.setImageURI(user.profileImageUri ?: Uri.parse("android.resource://scisrc.mobiledev.ecommercelayout/drawable/default_profile"))
        binding.fullNameEditText.setText(user.fullName)
        binding.emailEditText.setText(user.email)
        binding.addressEditText.setText(user.address)
    }

    private fun saveProfile() {
        val user = User(
            profileImageUri = profileImageUri,
            fullName = binding.fullNameEditText.text.toString(),
            email = binding.emailEditText.text.toString(),
            address = binding.addressEditText.text.toString()
        )
        ProfileManager.saveUser(requireContext(), user)
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            profileImageUri = data?.data
            binding.profileImageView.setImageURI(profileImageUri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }
}
