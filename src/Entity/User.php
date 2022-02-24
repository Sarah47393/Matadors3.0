<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 * @ORM\Table(name="`user`")
 * * @UniqueEntity("CIN")
 */
class User
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=10)
     * @Assert\NotBlank(message="veuillez entrer un nom valid")
     */
    private $Nom;

    /**
     * @ORM\Column(type="string", length=10)
     * @Assert\NotBlank(message="veuillez entrer un prenom valid")
     */
    private $Prenom;
 /**
     * @ORM\Column(type="integer")
     * *@Assert\NotBlank(message="veuillez entrer un cin valid")
     */
    private $Password;
    /**
     * @ORM\Column(type="integer", unique=true)
     * *@Assert\NotBlank(message="veuillez entrer un cin valid")
     * * @Assert\Length(
     * min = 8,
     * max = 8,
     * minMessage = "Le nom d'un article doit comporter au moins {{ limit }} caractères",
     * maxMessage = "Le nom d'un article doit comporter au plus {{ limit }} caractères")
     */
    private $CIN;

    /**
     * @ORM\Column(type="string", length=10)
     */
    private $Role;

    /**
     * @ORM\Column(type="string", length=10)
     */
    private $Access;
    /**
     * @ORM\Column(type="string", length=255)
     *
     */
    private $image;
    /**
     * @ORM\Column(type="date")
     */
    private $datenaissance;

    /**
     * @ORM\OneToMany(targetEntity=Emplois::class, mappedBy="User", orphanRemoval=true)
     */
    private $emplois;

    public function __construct()
    {
        $this->emplois = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->Nom;
    }

    public function setNom(string $Nom): self
    {
        $this->Nom = $Nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->Prenom;
    }

    public function setPrenom(string $Prenom): self
    {
        $this->Prenom = $Prenom;

        return $this;
    }

    public function getCIN(): ?int
    {
        return $this->CIN;
    }

    public function setCIN(int $CIN): self
    {
        $this->CIN = $CIN;

        return $this;
    }
    public function getPassword(): ?int
    {
        return $this->Password;
    }

    public function setPassword(int $Password): self
    {
        $this->Password = $Password;

        return $this;
    }
    public function getRole(): ?string 
    {
        return $this->Role;
    }

    public function setRole(string $Role): self
    {
        $this->Role = $Role;

        return $this;
    }

    public function getAccess(): ?string
    {
        return $this->Access;
    }

    public function setAccess(string $Access): self
    {
        $this->Access = $Access;

        return $this;
    }
    public function getimage()
    {
        return $this->image;
    }

    public function setimage( $image)
    {
        $this->image = $image;

        return $this;
    }
    public function getdatenaissance(): ?\DateTimeInterface
    {
        return $this->datenaissance;
    }

    public function setdatenaissance(\DateTimeInterface $datenaissance): self
    {
        $this->datenaissance = $datenaissance;

        return $this;
    }

    /**
     * @return Collection|Emplois[]
     */
    public function getEmplois(): Collection
    {
        return $this->emplois;
    }

    public function addEmploi(Emplois $emploi): self
    {
        if (!$this->emplois->contains($emploi)) {
            $this->emplois[] = $emploi;
            $emploi->setUser($this);
        }

        return $this;
    }

    public function removeEmploi(Emplois $emploi): self
    {
        if ($this->emplois->removeElement($emploi)) {
            // set the owning side to null (unless already changed)
            if ($emploi->getUser() === $this) {
                $emploi->setUser(null);
            }
        }

        return $this;
    }
}